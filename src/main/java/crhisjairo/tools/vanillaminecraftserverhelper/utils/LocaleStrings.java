package crhisjairo.tools.vanillaminecraftserverhelper.utils;

public enum LocaleStrings {
    en_US("English"),
    es_ES("Espa\u00F1ol"),
    fr_CA("Fran\u00E7ais");

    private String name;


    LocaleStrings(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
