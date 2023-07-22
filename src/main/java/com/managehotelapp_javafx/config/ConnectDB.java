package com.managehotelapp_javafx.config;

import com.managehotelapp_javafx.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class ConnectDB {

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");

                settings.put(Environment.URL, "jdbc:postgresql://dpg-cimjn7l9aq07op9n1if0-a.singapore-postgres.render.com/manager_hotel_javafx");
                settings.put(Environment.USER, "daidung");
                settings.put(Environment.PASS,"h7nF4ML0dMuzu2tWrlrWBm8OpCKsXnI3");

//                // Local
//                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/manage_hotel_javafx");
//                settings.put(Environment.USER, "postgres");
//                settings.put(Environment.PASS,"123");
                
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

                settings.put(Environment.SHOW_SQL, true);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration
                        .setProperties(settings)
                        .addAnnotatedClass(BookingRoomEntity.class)
                        .addAnnotatedClass(BookingEntity.class)
                        .addAnnotatedClass(BookingServiceEntity.class)
                        .addAnnotatedClass(CouponEntity.class)
                        .addAnnotatedClass(CustomerEntity.class)
                        .addAnnotatedClass(CustomerTypeEntity.class)
                        .addAnnotatedClass(FacilityEntity.class)
                        .addAnnotatedClass(HotelEntity.class)
                        .addAnnotatedClass(HotelStatusEntity.class)
                        .addAnnotatedClass(InvoiceEntity.class)
                        .addAnnotatedClass(InvoiceStatusEntity.class)
                        .addAnnotatedClass(RevenueHotelDetailEntity.class)
                        .addAnnotatedClass(RevenueMonthEntity.class)
                        .addAnnotatedClass(RoomEntity.class)
                        .addAnnotatedClass(RoomFacilityEntity.class)
                        .addAnnotatedClass(RoomStatusEntity.class)
                        .addAnnotatedClass(RoomTypeEntity.class)
                        .addAnnotatedClass(ServiceEntity.class)
                        .addAnnotatedClass(StatusBookingEntity.class)
                        .addAnnotatedClass(StatusService.class)
                        .addAnnotatedClass(UserEntity.class)
                        .addAnnotatedClass(UserRoleEntity.class)
                        .addAnnotatedClass(UserStatusEntity.class);

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
