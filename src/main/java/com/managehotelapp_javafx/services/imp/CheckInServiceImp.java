package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.*;
import com.managehotelapp_javafx.entity.*;
import com.managehotelapp_javafx.repository.CustomerTypeRepository;
import com.managehotelapp_javafx.repository.RoomTypeRepository;
import com.managehotelapp_javafx.repository.ServicesRepository;
import com.managehotelapp_javafx.repository.imp.*;
import com.managehotelapp_javafx.services.CheckInService;
import com.managehotelapp_javafx.utils.session.SessionUser;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CheckInServiceImp implements CheckInService {
    RoomRepositoryImp roomRepository = new RoomRepositoryImp();
    BookingRepositoryImp bookingRepository = new BookingRepositoryImp() ;
    CustomerRepositoryImp customerRepository = new CustomerRepositoryImp();
    BookingRoomRepositoryImp bookingRoomRepositoryImp = new BookingRoomRepositoryImp();
    private CustomerEntity customer ;
    private List<RoomEntity> roomEntityList = new ArrayList<>();
    private Set<BookingRoomEntity> bookingRoomEntities =  new HashSet<>();
    private List<ServiceEntity > serviceEntities = new ArrayList<>();
    public CustomerEntity setCustomerByIDN(String idn)
    {
        return customer = customerRepository.getCustomerByPID(idn);
    }
    public void setCustomerType(String type)
    {
         customer.setCustomerType(new CustomerTypeRepositoryImp().findCustomerTypeByTitle(type));
    }
//    public BookingEntity getBookingByCustomerId(CustomerEntity customerEntity)
//    {
//        return  bookingEntity = bookingRepository.getBookingByCustomer(customerEntity);
//    }
    public void setRoomListByNames(Set<String> names)
    {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        for (String name : names)
        {
            roomEntityList.add(roomRepository.getRoomByRoomName(name));
        }
        this.roomEntityList = roomEntityList;
    }

    private BookingRoomEntity bookingRoomEntity = new BookingRoomEntity();
    private BookingEntity bookingEntity = new BookingEntity();
    public  List<StatusBookingEntity> getListOfCheckInBookingStatus()
    {
        return new StatusBookingRepositoryImp().findAll().stream().sorted(Comparator.comparingInt(StatusBookingEntity::getId))
            .collect(Collectors.toList()).stream().limit(2).toList();
    }
    public void setBookingStatus(String status)
    {
        bookingRoomEntity.setStatusBooking(new StatusBookingServiceImp()
                .statusBookingRepository.findAll().stream()
                .filter(f -> f.getTitle().equals(status)).toList().get(0));
    }
    public void setBookingServices(List<ServiceDTO> serviceDTOList)
    {
        ServicesRepository servicesRepository = new ServicesRepositoryImp();
        serviceDTOList.forEach(s -> this.serviceEntities.add(servicesRepository.findServicesById(s.getId())));
    }

    public BookingDTO getRoomDetail(){
        RoomEntity roomEntity = roomEntityList.get(0);
        Optional<BookingRoomEntity> getBookingRoomEntity = bookingRoomRepositoryImp.findAll().stream()
                .filter(item -> Objects.equals(item.getRoom().getRoomNumber(), roomEntity.getRoomNumber())
        ).findFirst();
        getBookingRoomEntity.ifPresent(object -> bookingRoomEntity = object);
        var bookingEntity = bookingRepository.findAllBooking().stream().filter(f -> f.getId() == bookingRoomEntity.getBooking().getId()).findFirst();
        bookingEntity.ifPresent(object -> this.bookingEntity = object);
        var item =  this.bookingEntity;
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setPhoneNumber(item.getCustomer().getPhone());
        bookingDTO.setPhoneNumber(item.getCustomer().getEmail());
//        bookingDTO.setStatus(item.getStatusBooking().getTitle());
        bookingDTO.setStatus("Reserved");
        bookingDTO.setCustomerIDN(item.getCustomer().getIdentity());
        bookingDTO.setCustomerName(item.getCustomer().getFullName());
        bookingDTO.setCheckInDate(item.getEstimateDateIn().toString());
        bookingDTO.setCheckOutDate(item.getEstimateDateOut().toString());
        bookingDTO.setBookingDate(item.getBookingDate().toString());
        return  bookingDTO;
    }
    @Override
    public boolean checkIn(BookingDTO bookingDTO) {
        try {
            if (customer == null) {
                customer = new CustomerEntity();
                customer.setFullName(bookingDTO.getCustomerName());
                customer.setIdentity(bookingDTO.getCustomerIDN());
                customer.setPhone(bookingDTO.getPhoneNumber());
                customerRepository.insertCustomerCheckIn(customer);
                customer = setCustomerByIDN(bookingDTO.getCustomerIDN());
            }
            StatusBookingRepositoryImp statusBookingRepository = new StatusBookingRepositoryImp();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BookingEntity bookingEntity = new BookingEntity();
            UserEntity userEntity = new UserRepositoryImp().getUserById(SessionUser.getInstance().getId());
            try {
                Date date1 = dateFormat.parse(bookingDTO.getBookingDate() + " 00:00:00");
                Date date2 = dateFormat.parse(bookingDTO.getCheckinDate() + " 00:00:00");
                Date date3 = dateFormat.parse(bookingDTO.getCheckOutDate() + " 00:00:00");
                bookingEntity.setBookingDate(new Timestamp(date1.getTime()));
                bookingEntity.setCustomer(customer);
                bookingEntity.setUserEntity(userEntity);
                bookingEntity.setEstimateDateIn(new Timestamp(date2.getTime()));
                bookingEntity.setEstimateDateOut(new Timestamp(date3.getTime()));
                bookingEntity.setNumAdult(bookingDTO.getAdultCount());
                bookingEntity.setNumChildren(bookingDTO.getChildrenCount());
                bookingEntity.setSpecialRequest(bookingDTO.getCustomerRequest());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            for (RoomEntity r : roomEntityList) {
                BookingRoomEntity bookingRoomEntity = new BookingRoomEntity();
                bookingRoomEntity.setRoom(r);
                bookingRoomEntity.setBooking(bookingEntity);
                bookingRoomEntity.setStatusBooking(statusBookingRepository.findByStatusByTitle(bookingDTO.getStatus()));
                roomRepository.updateCheckInRoom(r);
                bookingRoomEntities.add(bookingRoomEntity);
            }
            //bookingEntity.setStatusBooking(statusBookingRepository.findById(1).get(0));
            bookingEntity.setBookingRoomEntities(bookingRoomEntities);

            bookingRepository.insert(bookingEntity);
//            for (var b : bookingRoomEntities) {
//                bookingRoomRepositoryImp.insert(b);
//            }

            //customerRepository.updateCustomerStatus(customer);
        }
        catch (Exception e){
            return  false;
        }
        return true;
    }
    public  List<CustomerDTO> getListCustomer(){
        return  customerRepository.getCustomers().stream().map(item->{
            return new CustomerDTO(item.getFullName(),item.getPhone(),item.getEmail(),item.getAddress(), item.getPassportNo(), item.getIdentity());
        }).toList();
    }
    public CustomerDTO getCustomerByPID (String pid){
        var customer = customerRepository.getCustomerByPID(pid);
        return new CustomerDTO(customer.getFullName()
                 ,customer.getPhone(),customer.getEmail(),customer.getAddress(),customer.getPassportNo(),customer.getIdentity());
    }

    public List<String> getRoomType()
    {
        RoomTypeRepository roomTypeRepository = new RoomTypeRepositoryImp();
        return roomTypeRepository.findAll().stream()
                .map(RoomTypeEntity::getDescription).toList();
    }

    public List<RoomDTO> getRoomsByType(List<RoomDTO> listOfRooms, String roomType)
    {
        return listOfRooms.stream()
                .filter(item -> Objects.equals(item.getType(), roomType)).toList();
    }

    public List<ServiceDTO> getServices()
    {
        return new ServicesRepositoryImp().getServicesList().stream()
                .map(item-> new ServiceDTO(item.getId(),item.getQuantity(),item.getPrice(), item.getDescription())).toList();
    }
}
