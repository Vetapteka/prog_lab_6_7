package model;

import utils.Reader;

import java.io.PrintStream;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Flat implements Serializable, Comparable<Flat> {

    /**
     * The value of the field must be greater than 0, The value of this field must be unique, The value of this field must be generated automatically
     */
    private final int id;
    /**
     * Field cannot be null, String cannot be empty
     */
    private final String name;
    /**
     * Field cannot be null
     */
    private final Coordinates coordinates;
    /**
     * Field cannot be null, The value of this field must be generated automatically
     */
    private final ZonedDateTime creationDate;
    /**
     * Field value must be greater than 0
     */
    private final Long area;
    /**
     * Maximum field value: 10, Field value must be greater than 0
     */
    private final int numberOfRooms;
    /**
     * Field can be null
     */
    private final Furnish furnish;
    /**
     * Field cannot be null
     */
    private final View view;
    /**
     * Field can be null
     */
    private final Transport transport;
    /**
     * Field can be null
     */
    private final House house;

    public Flat(int id, String name, Coordinates coordinates, ZonedDateTime creationDate,
                Long area, int numberOfRooms, Furnish furnish, View view,
                Transport transport, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;
    }

    /**
     * Initializes a newly created Flat object
     *
     * @param scanner input from where to read values
     * @param out     output from where to request values
     * @param id      id
     */
    public Flat(Scanner scanner, Integer id, PrintStream out) {
        this.id = id;
        creationDate = ZonedDateTime.now();
        name = Reader.readString(scanner, out, "Name: ");
        coordinates = new Coordinates(scanner, out);
        area = (Long) Reader.readNumber(Long::parseLong, scanner, out, "Area: ", 0, Long.MAX_VALUE);
        numberOfRooms = Reader.readNumber(Integer::parseInt, scanner, out, "NumberOfRooms: ", 0, 10).intValue();
        furnish = Reader.readEnum(Furnish.class, scanner, out, true);
        view = Reader.readEnum(View.class, scanner, out, false);
        transport = Reader.readEnum(Transport.class, scanner, out, true);
        out.println("enter value Home? y/n");

        house = (scanner.nextLine().equals("y")) ? new House(scanner, out) : null;

    }


    public House getHouse() {
        return house;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Long getArea() {
        return area;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public View getView() {
        return view;
    }

    public Transport getTransport() {
        return transport;
    }

    /**
     * @return Returns the string representation of this Flat.
     */
    @Override
    public String toString() {
        String ret;
        if (house == null) {
            ret = "{" + " id='" + id + "'" + ", name='" + name + "'" + ", coordinates='" + coordinates + "'"
                    + ", creationDate='" + creationDate.toString() + "'" + ", area='" + area + "'" + ", numberOfRooms='"
                    + numberOfRooms + "'" + ", furnish='" + furnish + "'" + ", view='" + view + "'" + ", transport='"
                    + transport + "'" + ", house='null'" + "}";
        } else {
            ret = "{" + " id='" + id + "'" + ", name='" + name + "'" + ", coordinates='" + coordinates + "'"
                    + ", creationDate='" + creationDate.toString() + "'" + ", area='" + area + "'" + ", numberOfRooms='"
                    + numberOfRooms + "'" + ", furnish='" + furnish + "'" + ", view='" + view + "'" + ", transport='"
                    + transport + "'" + ", house='" + house.toString() + "'" + "}";
        }
        return ret;

    }

    @Override

    public int compareTo(Flat b) {
        int n = Integer.compare(name.length(), b.getName().length());
        int r = Integer.compare(numberOfRooms, b.getNumberOfRooms());
        int t = Integer.compare(b.getTransport().ordinal(), transport.ordinal());
        int f = Integer.compare(b.getFurnish().ordinal(), furnish.ordinal());
        int v = Integer.compare(b.getView().ordinal(), view.ordinal());

        return Integer.compare((n + r + t + f + v), 3);
    }
}

