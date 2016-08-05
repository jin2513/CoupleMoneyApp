package lyj.money.book.couplemoney.common;

public class EventCode {

    /**
     * Key
     */
    public enum KeyType {
        FRAGMENT        ("fragment");   // FRAGMENT KEY

        public String key;
        KeyType(String key) {
            this.key = key;
        }
    }

    /**
     * 화면
     */
    public enum FragmentType {
        LOGIN           (0x0001);   // 로그인 화면

        public int nId;
        FragmentType(int nId) {this.nId = nId;}
    }
}
