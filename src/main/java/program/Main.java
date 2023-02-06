package program;

import models.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionUtils;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner string = new Scanner(System.in);
        boolean flag = true;
        Transaction transaction= null;
        Session context = HibernateSessionUtils.getSessionFactory().openSession();
        do {
            System.out.println("\n\n          CRUD Menu");
            System.out.println("--------------------------------------");
            System.out.println("0 - End Session ");
            System.out.println("1 - View Roles ");
            System.out.println("2 - Create new Role");
            System.out.println("3 - Delete");
            System.out.println("4 - UPDATE");

            System.out.print("\nSelect a Menu Option: ");
            try {
                int choice = Integer.parseInt(input.next()); // Get user input from the keyboard


                switch (choice) {
                    case 0 -> {
                        System.out.println("Goodbye");
                        context.close();
                        flag = false;
                    }
                    case 1 -> {
                        Query query = context.createQuery("FROM Role");
                        List<Role> list = query.list();

                        for (Role role : list) {
                            System.out.println("ID:" +role.getId() + "\tНазва: " + role.getName());
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter Role name");
                        String name = string.nextLine();
                        Role role = new Role();
                        role.setName(name);
                        context.save(role);
                    }
                    case 3 -> {
                        transaction =  context.beginTransaction();
                        System.out.println("Enter Role id to delete");
                        int id = Integer.parseInt(input.next());
                        Role role = (Role) context.get(Role.class, id);
                        System.out.println(role.getName());
                        context.delete(role);
                        transaction.commit();

                    }
                    case 4 -> {
                        System.out.println("Enter Role id to update");
                        int id = Integer.parseInt(input.next());
                        Role role = (Role) context.get(Role.class, id);
                        System.out.println(role.getName());
                        System.out.println("Enter Role new name");
                        String name = string.nextLine();
                        role.setName(name);
                        context.update(role);

                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        }
        while (flag);

    }

}