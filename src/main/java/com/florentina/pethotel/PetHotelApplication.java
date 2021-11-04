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
        zenHotel.setAddress(new Address("Ploiesti", "Romania", "Matei Basarab", 23, "042041"));
        zenHotel.setHasVeterinary(true);
        zenHotel.setRating(0.0);
        zenHotel.setPictureLink("https://scontent.fotp3-3.fna.fbcdn.net/v/t1.6435-9/131893037_176598604186647_5592366403523861440_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=e3f864&_nc_ohc=EqiRnPcjSFEAX_Ys7ob&_nc_ht=scontent.fotp3-3.fna&oh=a7dbd46af48ab3eee6b9724095402173&oe=61906407");
        zenHotel.setReviews(new ArrayList<>());
        petHotelRepository.save(zenHotel);

        PetHotel raiulAnimalelor = new PetHotel();
        raiulAnimalelor.setHotelName("Raiul animalelor");
        raiulAnimalelor.setPassword("123");
        raiulAnimalelor.setEmail("raiul-animalelor@gmail.com");
        raiulAnimalelor.setDescription("Hainute,jucarii,accesorii,mancare,recompense si cosmetice pentru animale.Servicii de cosmetica felina si canina,cat si hotel pentu animale.");
        raiulAnimalelor.setAddress(new Address("Ploiesti", "Romania", "Florilor", 25, "042049"));
        raiulAnimalelor.setHasVeterinary(true);
        raiulAnimalelor.setRating(0.0);
        raiulAnimalelor.setPictureLink("https://i.pinimg.com/736x/d0/d6/d7/d0d6d726d527e8fba7a29b4a1f84bea5--dog-boarding-kennels-dog-kennels.jpg");
        raiulAnimalelor.setReviews(new ArrayList<>());
        petHotelRepository.save(raiulAnimalelor);



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


        HotelOffer hotelOfferZenHotel = new HotelOffer();
        hotelOfferZenHotel.setRoomType(RoomType.GARDEN_ROOM_DOGS);
        hotelOfferZenHotel.setTotalRooms(3);
        hotelOfferZenHotel.setMealsPerDay(3);
        hotelOfferZenHotel.setWalkingPerDay(2);
        hotelOfferZenHotel.setPricePerDay(70);
        hotelOfferZenHotel.setPetHotel(petHotelRepository.getById(zenHotel.getId()));
        hotelOfferRepository.save(hotelOfferZenHotel);


        HotelOffer hotelOfferRaiulAnimalelor = new HotelOffer();
        hotelOfferRaiulAnimalelor.setRoomType(RoomType.LUXURY_ROOM_DOGS);
        hotelOfferRaiulAnimalelor.setTotalRooms(2);
        hotelOfferRaiulAnimalelor.setMealsPerDay(3);
        hotelOfferRaiulAnimalelor.setWalkingPerDay(3);
        hotelOfferRaiulAnimalelor.setPricePerDay(100);
        hotelOfferRaiulAnimalelor.setPetHotel(petHotelRepository.getById(raiulAnimalelor.getId()));
        hotelOfferRepository.save(hotelOfferRaiulAnimalelor);


    }

}
