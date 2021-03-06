package commands;

import model.Flat;
import model.House;
import model.MyCollection;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class CountByHouseCommand extends Command {
    private House house;

    public CountByHouseCommand() {
        super("count_by_house", "display the number of elements whose house field value is equal to the given one");
    }

    @Override
    public void setArgs(Scanner scanner, PrintStream out, List<String> args) {
        house = new House(scanner, out);
    }

    @Override
    public String execute(MyCollection myCollection) {
        StringBuilder sb = new StringBuilder();
        long count = myCollection.countByHouseName(house);
        sb.append("There are ").append(count).append(" such houses.");
        return sb.toString();
    }

}
