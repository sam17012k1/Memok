package com.example.memok;

public class Word {
    String defaults;
    String memok;
    private int image_id;
    int sound_id;

    public int getSound_id() {
        return sound_id;
    }

    public void setSound_id(int sound_id) {
        this.sound_id = sound_id;
    }

    int i=0;
    public Word(String defaults,String memok,int sound_id){
        this.defaults=defaults;
        this.memok=memok;
        this.sound_id=sound_id;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Word(String defaults, String memok, int image_id,int sound_id){
        this.defaults=defaults;
        this.memok=memok;
        this.image_id=image_id;
        this.sound_id=sound_id;
        i++;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getDefaults() {
        return defaults;
    }

    public void setDefaults(String defaults) {
        this.defaults = defaults;
    }

    public String getMemok() {
        return memok;
    }

    public void setMemok(String memok) {
        this.memok = memok;
    }
}
