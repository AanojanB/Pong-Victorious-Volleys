module com.game.pongvictoriousvolleys {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.game.pongvictoriousvolleys to javafx.fxml;
    exports com.game.pongvictoriousvolleys;
}