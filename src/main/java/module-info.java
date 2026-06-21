module com.luisotaviodias {
    requires transitive javafx.graphics;
    requires javafx.controls;

    exports com.luisotaviodias;

    opens com.luisotaviodias.view to javafx.graphics;
    opens com.luisotaviodias.model to javafx.base;
}
