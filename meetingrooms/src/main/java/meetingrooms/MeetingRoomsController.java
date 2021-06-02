package meetingrooms;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MeetingRoomsController {

    private Scanner scanner = new Scanner(System.in);

    private MeetingRoomsService meetingRoomsService =
            new MeetingRoomsService(new InMemoryMeetingRoomsRepository());

//    private MeetingRoomsService meetingRoomsService =
//            new MeetingRoomsService(new MariaDbMeetingRoomsRepository());

    public static void main(String[] args) {
        new MeetingRoomsController().start();
    }

    private void start() {
        boolean run = true;
        while (run) {
            printMenu();
            String select = scanner.nextLine();
            switch (select) {
                case "0" -> addMeetingRoom();
                case "1" -> System.out.println(meetingRoomsService.findAllOrderedByName()
                        .stream()
                        .map(MeetingRoom::getName)
                        .collect(Collectors.joining(",")));
                case "2" -> System.out.println(meetingRoomsService.findAllReverseOrderedByName()
                        .stream().map(MeetingRoom::getName)
                        .collect(Collectors.joining(",")));
                case "3" -> System.out.println(meetingRoomsService.everySecondMeetingRooms()
                        .stream().map(MeetingRoom::getName)
                        .collect(Collectors.joining(",")));
                case "4" -> System.out.println(meetingRoomsService.findMeetingRoomsByAreas());
                case "5" -> findMeetingRoomByName();
                case "6" -> findMeetingRoomByPartOfName();
                case "7" -> findMeetingRoomByArea();
                case "8" -> run = false;
            }
        }
    }

    private void findMeetingRoomByArea() {
        System.out.print("Terület: ");
        int area = scanner.nextInt();
        scanner.nextLine();
        List<MeetingRoom> result = meetingRoomsService.findMeetingRoomByArea(area);
        if (result.isEmpty()) {
            System.out.println("Nincs ilyen terem!");
        }else {
            System.out.println(result);
        }
    }

    private void findMeetingRoomByName() {
        System.out.print("Név: ");
        String name = scanner.nextLine();
        try {
            System.out.println(meetingRoomsService.findMeetingRoomByName(name));
        }catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findMeetingRoomByPartOfName() {
        System.out.print("Névtöredék: ");
        String partOfName = scanner.nextLine();
        List<MeetingRoom> result = meetingRoomsService.findMeetingRoomByPartOfName(partOfName);
        if (result.isEmpty()) {
            System.out.println("Nincs ilyen nevű terem!");
        }else {
            System.out.println(result);
        }
    }

    private void addMeetingRoom() {
        System.out.print("Name of meetingroom: ");
        String name = scanner.nextLine();
        System.out.print("Width of meetingroom: ");
        int width = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Length of meetingroom: ");
        int length = scanner.nextInt();
        scanner.nextLine();
        meetingRoomsService.add(name, width, length);

    }

    private void printMenu() {
        System.out.println("""
                0. Tárgyaló rögzítése
                1. Tárgyalók névsorrendben
                2. Tárgyalók név alapján visszafele sorrendben
                3. Minden második tárgyaló
                4. Területek
                5. Keresés pontos név alapján
                6. Keresés névtöredék alapján
                7. Keresés terület alapján
                8. Kilépés
                """);
    }
}
