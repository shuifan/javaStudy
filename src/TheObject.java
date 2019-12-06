/**
 * @author fandong
 * @create 2019/7/26
 */
public class TheObject{

    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    private TheObject(TheObjectBuilder theObjectBuilder){
        this.a = theObjectBuilder.a;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }


    public static class TheObjectBuilder{
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;

        public TheObjectBuilder() {
        }

        public TheObjectBuilder setA(String a) {
            this.a = a;
            return this;
        }

        public TheObjectBuilder setB(String b) {
            this.b = b;
            return this;
        }

        public TheObjectBuilder setC(String c) {
            this.c = c;
            return this;
        }

        public TheObjectBuilder setD(String d) {
            this.d = d;
            return this;
        }

        public TheObjectBuilder setE(String e) {
            this.e = e;
            return this;
        }

        public TheObjectBuilder setF(String f) {
            this.f = f;
            return this;
        }

        public TheObjectBuilder setG(String g) {
            this.g = g;
            return this;
        }

        public TheObjectBuilder setH(String h) {
            this.h = h;
            return this;
        }

        public TheObject build(){
            return new TheObject(this);
        }
    }
}
