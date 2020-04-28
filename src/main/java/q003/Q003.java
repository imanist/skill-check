package q003;

import org.apache.commons.lang3.StringUtils;
import sun.security.util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {
        InputStream inputStream = openDataFile();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(" ");
            }
            String[] str = sb.toString().split(" ");
            // 単語とカウントを持ったマップを用意
            Map<String, Integer> map = new HashMap<String, Integer>();
            int count = 0;
            String replaceStr = ",.:";
            for (String s : str) {
                String str1 = StringUtils.strip(s, replaceStr);
                if (!str1.equals("I")) {
                    str1 = str1.toLowerCase();
                }

                if (map.containsKey(str1)) {
                    count = map.get(str1) + 1;
                } else {
                    count = 1;
                }
                map.put(str1, count);
            }

            for (String key : map.keySet()) {
                System.out.printf("%s=%d%n", key, map.get(key));
            }

        } catch (IOException e) {
            e.printStackTrace();
        };
    }
}
// 完成までの時間: 20分