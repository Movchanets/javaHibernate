package program;

import models.Answer;
import models.Question;
import models.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionUtils;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void Tests() {
        Scanner input = new Scanner(System.in);
        Scanner string = new Scanner(System.in);
        boolean flag = true;
        Transaction transaction = null;
        Session context = HibernateSessionUtils.getSessionFactory().openSession();
        do {
            System.out.println("\n\n       Tests Menu");
            System.out.println("--------------------------------------");
            System.out.println("-1 - End Session ");
            System.out.println("0 - Do test ");
            System.out.println("1 - View Questions");
            System.out.println("2 - Create new Question");
            System.out.println("3 - Edit Question");
            System.out.println("4 - Delete Question");

            System.out.print("\nSelect a Menu Option: ");
            try {
                int choice = Integer.parseInt(input.next()); // Get user input from the keyboard


                switch (choice) {
                    case -1 -> {
                        System.out.println("Goodbye");
                        context.close();
                        flag = false;
                    }
                    case 0 -> {
                        System.out.println("Your grade is " + StartTest());
                    }
                    case 1 -> {
                        showQuestions();
                    }
                    case 2 -> {
                        addQuestion();
                    }
                    case 3 -> {

                        EditQuestion();

                    }
                    case 4 -> {
                        transaction = context.beginTransaction();
                        System.out.println("Enter Question id to delete");
                        List<Question> questions = context.createQuery("FROM Question").list();
                        int id = Integer.parseInt(input.next());
                        if (id >= 0 && questions.size() < id - 1) {

                            System.out.println(questions.get(id).getName());
                            questions.remove(questions.get(id));
                        }


                        transaction.commit();

                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        }
        while (flag);
    }

    public static void EditQuestion() {
        showQuestions();
        try (Session context = HibernateSessionUtils.getSessionFactory().openSession()) {
            Scanner in = new Scanner(System.in);

            System.out.println("Enter Question id to edit");
            int id = Integer.parseInt(in.nextLine());
            List<Question> questions = context.createQuery("FROM Question").list();

            if (0 < id && questions.size() < id - 1) {
                return;
            }
            Question question = questions.get(id - 1);
            String action = "";
            do {
                Transaction transaction = context.beginTransaction();
                System.out.println("Вкажіть дію:");
                System.out.println(question.toString());
                System.out.println("0 - Вийти 1-Редагувати текст, 2-Редагувати відповіді на питання по id, 3-Видалити  питання ");
                action = in.nextLine();

                switch (action) {
                    case "1" -> {
                        String text = in.nextLine();
                        question.setName(text);
                        context.update(question);
                    }
                    case "2" -> {
                        System.out.println("Enter answer id");
                        int Aid = Integer.parseInt(in.nextLine());
                        Answer cur = null;
                        if (Aid >= 0 && question.getAnswers().size() > Aid - 1) {
                            cur = question.getAnswers().get(Aid - 1);
                        } else {
                            continue;
                        }
                        System.out.println("1 - Змінити IsTrue   2- Додати Відповідь 3 - Редагувати відповідь. 4 - Видалити відповідь.");
                        int ch = Integer.parseInt(in.nextLine());

                        switch (ch) {
                            case 1 -> {
                                cur.setTrue(!cur.isTrue());
                            }
                            case 3 -> {
                                String text = in.nextLine();
                                cur.setText(text);
                            }
                            case 2 -> {
                                System.out.println("Вкажіть відповідь:");
                                String text = in.nextLine();
                                System.out.println("1-правильно, 2-невірно");
                                boolean isTrue = Byte.parseByte(in.nextLine()) == 1;
                                Answer answer = new Answer();
                                answer.setText(text);
                                answer.setTrue(isTrue);
                                answer.setQuestion(question);
                                question.getAnswers().add(answer);
                                context.update(question);
                            }
                            case 4 -> {
                                question.getAnswers().remove(cur);
                                cur.setQuestion(null);
                                context.delete(cur);
                            }
                        }
                        transaction.commit();
                    }
                    case "3" -> {

                        context.delete(question);
                        transaction.commit();
                    }
                }
            } while (!action.equals("0"));

        }
    }

    public static int StartTest() {

        try (Session context = HibernateSessionUtils.getSessionFactory().openSession()) {
            Scanner input = new Scanner(System.in);
            int grade = 0;
            Query query = context.createQuery("FROM Question");
            List<Question> list = query.list();
            for (Question q : list) {
                System.out.println(q);
                int answer = Integer.parseInt(input.nextLine());
                List<Answer> answers = q.getAnswers();
                if (answers.size() > answer - 1 && 0 >= answer - 1) {
                    if (answers.get(answer - 1).isTrue()) {
                        grade += 2;
                    }
                }
            }
            return grade;
        }
    }

    public static void main(String[] args) {
        Tests();
    }

    private static void showQuestions() {
        try (Session context = HibernateSessionUtils.getSessionFactory().openSession()) {

            Query query = context.createQuery("FROM Question");
            List<Question> list = query.list();
            int i = 0;
            for (Question q : list)
                System.out.println(++i + " : " + q);
        }
    }

    private static void addQuestion() {
        try (Session context = HibernateSessionUtils.getSessionFactory().openSession()) {
            Scanner in = new Scanner(System.in);
            Transaction tx = context.beginTransaction();
            System.out.println("Вкажіть питання:");
            String questionText = in.nextLine();
            Question q = new Question();
            q.setName(questionText);
            context.save(q);
            String action = "";
            do {
                System.out.println("Вкажіть відповідь:");
                String text = in.nextLine();
                System.out.println("1-правильно, 2-невірно");
                boolean isTrue = Byte.parseByte(in.nextLine()) == 1;
                Answer answer = new Answer();
                answer.setText(text);
                answer.setTrue(isTrue);
                answer.setQuestion(q);
                context.save(answer);
                System.out.println("0. Вихід");
                System.out.println("1. Наступний варіант");
                System.out.println("->_");
                action = in.nextLine();
            } while (!action.equals("0"));
            tx.commit();
        }
    }
}