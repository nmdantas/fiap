package br.com.fiap.educalab.enumerators;

import android.content.pm.ApplicationInfo;

public enum CategoryType {
    AUDIO(ApplicationInfo.CATEGORY_AUDIO, "Audio"),
    GAME(ApplicationInfo.CATEGORY_GAME, "Jogos"),
    IMAGE(ApplicationInfo.CATEGORY_IMAGE, "Imagem"),
    MAPS(ApplicationInfo.CATEGORY_MAPS, "Mapas"),
    NEWS(ApplicationInfo.CATEGORY_NEWS, "Noticias"),
    PRODUCTIVITY(ApplicationInfo.CATEGORY_PRODUCTIVITY, "Produtividade"),
    SOCIAL(ApplicationInfo.CATEGORY_SOCIAL, "Social"),
    UNDEFINED(ApplicationInfo.CATEGORY_UNDEFINED, "Indefinido"),
    VIDEO(ApplicationInfo.CATEGORY_VIDEO, "Video");

    private int code;
    private String description;

    CategoryType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CategoryType getByCode(int code) {
        for (CategoryType category : CategoryType.values()) {
            if (category.getCode() == code) {
                return category;
            }
        }

        return CategoryType.UNDEFINED;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
