package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Drizzle {
    public static void main(String[] args) throws ParseException {
        System.out.println("请输入日期：示例 2021-07-01");
        Scanner scanner = new Scanner(System.in);
        String dateStr = scanner.next();
        System.out.println("请输入总金额: 示例 1000");
        Integer total = scanner.nextInt();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse(dateStr);
        calcAmount(total);
        for (int i = 0; i < 31; i = i + ThreadLocalRandom.current().nextInt(1, 2)) {
            Date data = getData(startDate, i);
            String date = simpleDateFormat.format(data);
            String time = getTime();
            System.out.println(date + " " + time);
        }
    }

    public static void calcAmount(Integer total) {
        while (total >= 100) {
            int amount = ThreadLocalRandom.current().nextInt(82, 96);
            System.out.println(amount);
            total -= amount;
        }
        System.out.println(total);
    }

    public static String getTime() {
        return ThreadLocalRandom.current().nextInt(20, 22) + ":"
                + ThreadLocalRandom.current().nextInt(10, 59) + ":"
                + String.format("%02d", ThreadLocalRandom.current().nextInt(0, 50));
    }

    public static Date getData(Date data, int index) {
        int step = index > 0 ? 1 : -1;
        int i = 0;
        int datAbs = Math.abs(index);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        while (i < datAbs) {
            calendar.add(Calendar.DATE, step);
            i++;
            //如果需要跳过更多的日期，可以在if中添加条件
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                i--;
            }
        }
        return calendar.getTime();
    }


}
