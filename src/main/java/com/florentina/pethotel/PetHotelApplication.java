package com.florentina.pethotel;

import com.florentina.pethotel.components.Address;
import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import com.florentina.pethotel.hotel.room.HotelOffer;
import com.florentina.pethotel.hotel.room.HotelOfferRepository;
import com.florentina.pethotel.hotel.enums.RoomType;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@SpringBootApplication
@AllArgsConstructor
public class PetHotelApplication {
private final PetHotelRepository petHotelRepository;
private final HotelOfferRepository hotelOfferRepository;
    public static void main(String[] args) {
        SpringApplication.run(PetHotelApplication.class, args);
    }

    @PostConstruct
    public void createData(){

        PetHotel zenHotel = new PetHotel();
        zenHotel.setHotelName("Zen Hotel");
        zenHotel.setPassword("123");
        zenHotel.setEmail("zen-hotel@gmail.com");
        zenHotel.setDescription("Experiența noastră se reflectă în modul în care am organizat spațiile de cazare și cele de ieșire, fluxul pet hotelului, alegerea furnizorilor, colaboratorilor și nu în ultimul rând, a setului de reguli și condiții pe care e necesar să le îndepliniți la cazare. Prin respectarea indicațiilor noastre, ne oferiți astfel sprijinul în a acorda toată atenția și grija noastră pentru cățelul, pisica sau animăluțul dvs. de companie.");
        zenHotel.setAddress(new Address("Bucuresti", "Romania", "Matei Basarab", 23, "042041"));
        zenHotel.setHasVeterinary(true);
        zenHotel.setRating(0.0);
        zenHotel.setPictureLink("https://scontent.fotp3-3.fna.fbcdn.net/v/t1.6435-9/131893037_176598604186647_5592366403523861440_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=e3f864&_nc_ohc=EqiRnPcjSFEAX_Ys7ob&_nc_ht=scontent.fotp3-3.fna&oh=a7dbd46af48ab3eee6b9724095402173&oe=61906407");
        zenHotel.setReviews(new ArrayList<>());
        petHotelRepository.save(zenHotel);



        PetHotel planetHam = new PetHotel();
        planetHam.setHotelName("Planeta Ham Ham");
        planetHam.setPassword("123");
        planetHam.setEmail("planeta-ham@gmail.com");
        planetHam.setDescription("Servicii si facilitati incluse: Oricand aveti nevoie sa va cazati catelul (sau pisica) intr-un loc sigur si accesibil, in spatii de interior, la toate categoriile de pret, Planeta HamHam va sta la dispozitie. Fie ca plecati din oras, va lasati catelul la gradinita sau doriti sa-l educati printr-un curs intensiv de dresaj, veti gasi aici cel mai confortabil pethotel.");
        planetHam.setAddress(new Address("Bucuresti", "Romania", "Barbu Vacarescu", 162, "042041"));
        planetHam.setHasVeterinary(false);
        planetHam.setRating(0.0);
        planetHam.setPictureLink("https://transilvaniareporter.ro/wp-content/uploads/2017/07/20031760_155565278337634_2814478043804705301_n.jpg");
        planetHam.setReviews(new ArrayList<>());
        petHotelRepository.save(planetHam);


        HotelOffer hotelOffer = new HotelOffer();
        hotelOffer.setRoomType(RoomType.GARDEN_ROOM_DOGS);
        hotelOffer.setTotalRooms(3);
        hotelOffer.setMealsPerDay(3);
        hotelOffer.setWalkingPerDay(2);
        hotelOffer.setPricePerDay(70);
        hotelOffer.setPetHotel(petHotelRepository.getById(zenHotel.getId()));
        hotelOfferRepository.save(hotelOffer);


    }

}
