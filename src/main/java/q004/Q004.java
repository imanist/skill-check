package q004;

/**
 * Q004 ソートアルゴリズム
 *
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 *
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 *
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {
    public static void main(String[] args) {
        int i=0;
        ListManager data = new ListManager();
        // 要素数を取得
        int size = data.size();
        while(i<size) {
            for (int c=i+1; c<size; c++) {
                if (data.compare(i, c) == 1) {
                    data.exchange(i, c);
                }
            }
            i++;
        }
        data.checkResult();
    }
}
// 完成までの時間: 7分