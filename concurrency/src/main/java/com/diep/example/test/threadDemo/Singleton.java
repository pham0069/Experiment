package com.diep.example.test.threadDemo;

public class Singleton {
    public static void main (String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> User.getUser()).start();
        }
    }
}

class User {
    private static User user = null;
    static Object lock = new Object();

    private User()
    {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    public static User getUser()
    {

        synchronized(lock)
        {
            if(user == null)
            {
                user = new User();
                System.out.println("Instantiating user object");
            }
            else
            {
                System.out.println("User object already created");
            }
        }


        return user;
    }
}