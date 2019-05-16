package pl.edu.agh.mwo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TripManagerTest {

	TripManager tripManager;
	Trip trip;

	@Before
	public void setUp() {
		tripManager = new TripManager();
		trip = new Trip("nazwa", "opis");
	}

	@Test
	public void testAdd() throws TripAlreadyExistsException {
		assertEquals(0, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
	}

	@Test(expected = TripAlreadyExistsException.class)
	public void testAddTripTwice() throws TripAlreadyExistsException {
		assertEquals(0, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		tripManager.add(trip);
	}

	@Test
	public void testRemoveTrip() throws Exception {
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		tripManager.remove(trip.getName());
		assertEquals(0, tripManager.getTrips().size());
		fail("chcemy zespuc");
	}

	@Test
	public void testFindTripByDescription() throws TripAlreadyExistsException {
		Trip trip = new Trip("Bahamy bez mamy", "Obiazdowa wycieczka wokół miasteczka");
		tripManager.add(trip);
		List<Trip> results = tripManager.findTrip("hawaje");
		assertEquals(0, results.size());
	}

	@Test
	public void testFindTripByComment() throws TripAlreadyExistsException {
		Photo photo = new Photo();
		photo.setComment("alewidoki");
		trip.addPhoto(photo);

		Photo photoA = new Photo();
		photoA.setComment("superwygladasz");

		Trip tripA = new Trip("Bahamy dla mamy", "Mocne drinki w basenie");
		tripA.addPhoto(photoA);

		tripManager.add(trip);
		tripManager.add(tripA);

		List<Trip> results = tripManager.findTrip("superwygladasz");
		assertEquals(1, results.size());
		assertEquals(tripA, results.get(0));

		trip.setDescription("Test codecove");
	}

	@Test
	public void testFindByEmptyKeyword() throws TripAlreadyExistsException {
		Photo photo = new Photo();
		photo.setComment("alewidoki");
		trip.addPhoto(photo);

		Photo photoA = new Photo();
		photoA.setComment("superwygladasz");
		Trip tripA = new Trip("Bahamy dla mamy", "Mocne drinki w basenie");
		tripA.addPhoto(photoA);

		tripManager.add(trip);
		tripManager.add(tripA);

		List<Trip> results = tripManager.findTrip("");
		assertEquals(2, results.size());
	}	
}
