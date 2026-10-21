package org.example.pruebajavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private ListView<String> listaComandos;

    @FXML
    private TextArea terminalSalida;

    @FXML
    private TextField campoComando;

    private final ObservableList<String> comandos =
            FXCollections.observableArrayList(
                    "help", "dir", "cd", "mkdir",
                    "rmdir", "type", "ipconfig",
                    "systeminfo", "cls"
            );

    // Sistema de archivos simulado
    private final List<String> carpetas =
            new ArrayList<>();

    @FXML
    public void initialize() {
        listaComandos.setItems(comandos);

        terminalSalida.setText(
                "CMD for Dummies\n" +
                        "Escribe help para ver los comandos.\n\n"
        );

        // Al seleccionar un comando, lo ponemos en el campo
        listaComandos.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, anterior, nuevo) -> {
                    if (nuevo != null) {
                        campoComando.setText(nuevo);
                        campoComando.requestFocus();
                    }
                });
    }

    @FXML
    private void ejecutarComando() {
        String entrada = campoComando.getText().trim();

        if (entrada.isEmpty()) {
            return;
        }

        terminalSalida.appendText("C:\\> " + entrada + "\n");

        String[] partes = entrada.split("\\s+", 2);
        String comando = partes[0].toLowerCase();
        String argumento = partes.length > 1
                ? partes[1]
                : "";

        switch (comando) {
            case "help":
                terminalSalida.appendText(
                        "Comandos disponibles:\n" +
                                "dir, cd, mkdir, rmdir, cls\n"
                );
                break;

            case "dir":
                terminalSalida.appendText(
                        "Contenido del directorio:\n"
                );

                if (carpetas.isEmpty()) {
                    terminalSalida.appendText(
                            "(No hay carpetas)\n"
                    );
                } else {
                    carpetas.forEach(carpeta ->
                            terminalSalida.appendText(
                                    "<DIR> " + carpeta + "\n"
                            )
                    );
                }
                break;

            case "mkdir":
                if (argumento.isBlank()) {
                    terminalSalida.appendText(
                            "Uso: mkdir nombre_carpeta\n"
                    );
                } else if (carpetas.contains(argumento)) {
                    terminalSalida.appendText(
                            "La carpeta ya existe.\n"
                    );
                } else {
                    carpetas.add(argumento);
                    terminalSalida.appendText(
                            "Carpeta creada: " + argumento + "\n"
                    );
                }
                break;

            case "rmdir":
                if (carpetas.remove(argumento)) {
                    terminalSalida.appendText(
                            "Carpeta eliminada: " + argumento + "\n"
                    );
                } else {
                    terminalSalida.appendText(
                            "La carpeta no existe.\n"
                    );
                }
                break;

            case "cd":
                terminalSalida.appendText(
                        "La navegación se implementará más adelante.\n"
                );
                break;

            case "ipconfig":
            case "systeminfo":
                terminalSalida.appendText(
                        "Este comando todavía no está implementado.\n"
                );
                break;

            case "cls":
                terminalSalida.clear();
                campoComando.clear();
                return;

            default:
                terminalSalida.appendText(
                        "'" + comando + "' no se reconoce como comando.\n"
                );
        }

        campoComando.clear();
    }
}