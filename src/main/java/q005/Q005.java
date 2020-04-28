package q005;

import q003.Q003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {

    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {
        List<WorkData> workList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(openDataFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                // オブジェクトにセット
                WorkData data = createWorkData(line);
                if (!Objects.isNull(data)) {
                    workList.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 役職毎
        Map<String, Integer> positionMap = getPositionTime(workList);
        for (String key : positionMap.keySet()) {
            System.out.printf("%s=%s%n", key, createTime(positionMap.get(key)));
        }
        // Pcode毎
        Map<String, Integer> pCodeMap = getPCodeTime(workList);
        for (String key : pCodeMap.keySet()) {
            System.out.printf("%s=%s%n", key, createTime(pCodeMap.get(key)));
        }
        // 社員番号毎
        Map<String, Integer> numberMap = getNumberTime(workList);
        for (String key : numberMap.keySet()) {
            System.out.printf("%s=%s%n", key, createTime(numberMap.get(key)));
        }
    }

    private static WorkData createWorkData(String line) {
        try {
            String[] str = line.split(",");
            return new WorkData(str[0],str[1],str[2],str[3],Integer.parseInt(str[4]));
        } catch (Exception e) {
            return null;
        }
    }

    private static Map<String, Integer> getPositionTime(List<WorkData> workDataList) {
        Map<String, Integer> map = new HashMap<>();
        int time = 0;
        for (WorkData data : workDataList) {
            if (map.containsKey(data.getPosition())) {
                time = map.get(data.getPosition()) + data.getWorkTime();
            } else {
                time = data.getWorkTime();
            }
            map.put(data.getPosition(), time);
        }
        return map;
    }

    private static Map<String, Integer> getPCodeTime(List<WorkData> workDataList) {
        Map<String, Integer> map = new HashMap<>();
        int time = 0;
        for (WorkData data : workDataList) {
            if (map.containsKey(data.getPCode())) {
                time = map.get(data.getPCode()) + data.getWorkTime();
            } else {
                time = data.getWorkTime();
            }
            map.put(data.getPCode(), time);
        }
        return map;
    }

    private static Map<String, Integer> getNumberTime(List<WorkData> workDataList) {
        Map<String, Integer> map = new HashMap<>();
        int time = 0;
        for (WorkData data : workDataList) {
            if (map.containsKey(data.getNumber())) {
                time = map.get(data.getNumber()) + data.getWorkTime();
            } else {
                time = data.getWorkTime();
            }
            map.put(data.getNumber(), time);
        }
        return map;
    }

    private static String createTime(int time) {
        System.out.println(time);
        int hour = time / 60;
        int min =  time-(hour*60);

        StringBuilder buff = new StringBuilder();
        buff.append(hour);
        buff.append("時間");
        buff.append(min);
        buff.append("分");
        return buff.toString();

    }

}

// 完成までの時間: 45分