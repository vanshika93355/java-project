import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Room class
class Room {
    private int roomNumber;
    private boolean isAvailable;
    private ImageIcon roomImage;
    private ImageIcon bookedImage;

    public Room(int roomNumber, String imagePath, String bookedImagePath) {
        this.roomNumber = roomNumber;
        this.isAvailable = true;
        this.roomImage = new ImageIcon(imagePath);
        this.bookedImage = new ImageIcon(bookedImagePath);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void cancelRoom() {
        isAvailable = true;
    }

    public ImageIcon getRoomImage() {
        return isAvailable ? roomImage : bookedImage;
    }
}

// Customer class
class Customer {
    private String name;
    private String contactInfo;

    public Customer(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

// Booking class
class Booking {
    private Room room;
    private Customer customer;

    public Booking(Room room, Customer customer) {
        this.room = room;
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }
}

// Hotel class
class HOTEL {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public HOTEL(int numberOfRooms) {
        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(new Room(i, "images/room" + i + ".jpg", "images/booked_room.jpg"));
        }
    }

    public void checkRoomAvailability(JPanel panel) {
        panel.removeAll();
        JLabel title = new JLabel("Available Rooms");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(title);

        for (Room room : rooms) {
            if (room.isAvailable()) {
                JPanel roomPanel = new JPanel();
                roomPanel.setLayout(new BorderLayout());
                JLabel roomLabel = new JLabel("Room Number: " + room.getRoomNumber());
                JLabel imageLabel = new JLabel(room.getRoomImage());
                roomPanel.add(roomLabel, BorderLayout.NORTH);
                roomPanel.add(imageLabel, BorderLayout.CENTER);
                panel.add(roomPanel);
            }
        }

        panel.revalidate();
        panel.repaint();
    }

    public int getAvailableRoomCount() {
        int count = 0;
        for (Room room : rooms) {
            if (room.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public void bookRoom(int roomNumber, Customer customer) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.bookRoom();
                bookings.add(new Booking(room, customer));
                JOptionPane.showMessageDialog(null,
                        "Room " + roomNumber + " booked successfully for " + customer.getName() +
                                "\nRemaining available rooms: " + getAvailableRoomCount() +
                                "\n\nThank you for booking!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Room " + roomNumber + " is not available.");
    }

    public void cancelBooking(int roomNumber, Customer customer) {
        for (Booking booking : bookings) {
            if (booking.getRoom().getRoomNumber() == roomNumber &&
                    booking.getCustomer().getName().equals(customer.getName())) {
                booking.getRoom().cancelRoom();
                bookings.remove(booking);
                JOptionPane.showMessageDialog(null, "Booking for room " + roomNumber + " has been cancelled.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Booking for room " + roomNumber + " not found.");
    }
}

// Main App
class HotelManagementApp {
    public static void main(String[] args) {
        String menu = "Welcome to Group Hostel\n\nChoose an option:\n1 - Book a Room\n2 - Cancel a Booking\n3 - Check Room Availability";
        String choice = JOptionPane.showInputDialog(null, menu, "Group Hostel", JOptionPane.PLAIN_MESSAGE);

        if (choice == null) return;

        HOTEL hotel = new HOTEL(10);

        JFrame frame = new JFrame("Hotel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));

        switch (choice) {
            case "1":
                String name = JOptionPane.showInputDialog("Enter Customer Name:");
                String contact = JOptionPane.showInputDialog("Enter Contact Info:");
                String roomNumStr = JOptionPane.showInputDialog("Enter Room Number to Book:");
                if (name != null && contact != null && roomNumStr != null) {
                    try {
                        int roomNumber = Integer.parseInt(roomNumStr);
                        hotel.bookRoom(roomNumber, new Customer(name, contact));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid room number.");
                    }
                }
                break;

            case "2":
                String cname = JOptionPane.showInputDialog("Enter Customer Name:");
                String ccontact = JOptionPane.showInputDialog("Enter Contact Info:");
                String croomStr = JOptionPane.showInputDialog("Enter Room Number to Cancel:");
                if (cname != null && ccontact != null && croomStr != null) {
                    try {
                        int roomNumber = Integer.parseInt(croomStr);
                        hotel.cancelBooking(roomNumber, new Customer(cname, ccontact));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid room number.");
                    }
                }
                break;

            case "3":
                hotel.checkRoomAvailability(roomPanel);
                frame.add(roomPanel);
                frame.setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Invalid choice. Exiting.");
                break;
        }
    }
}
