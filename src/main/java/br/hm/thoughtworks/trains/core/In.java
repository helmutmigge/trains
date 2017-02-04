package br.hm.thoughtworks.trains.core;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *  Prove metodos para ler strings e números da entrada padrão.
 *
 * Created by helmutmigge on 04/02/2017.
 */
public final class In implements Closeable {

    private static final Pattern EVERYTHING_PATTERN
            = Pattern.compile("\\A");

    private static Pattern WHITESPACE_PATTERN = Pattern.compile(
            "\\p{javaWhitespace}+");

    private final Scanner scanner;

    /**
     *  Inicializado com uma stream para entrada padrão
     */
    public In(){
        this.scanner = new Scanner(new BufferedInputStream(System.in),Charset.defaultCharset().name());
    }

    /**
     * Inicializado com o arquivo.
     *
     * @param  file arquivo
     * @throws IllegalArgumentException Se não poder abrir o {@code file}
     * @throws IllegalArgumentException Se {@code file} for {@code null}
     */
    public In(File file) {
        if (file == null) throw new IllegalArgumentException("file argument is null");
        try {
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), Charset.defaultCharset().name());
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + file, ioe);
        }
    }

    /**
     * Inicializa com o conteudo do nome do arquivo.
     *
     * @param  filename nome do arquivo
     * @throws IllegalArgumentException Se não poder abrir o {@code filename}
     * @throws NullPointerException Se {@code name} for (@code null)
     */
    public In(String filename) {
        if (filename == null) throw new IllegalArgumentException("argument is null");
        try {
            // first try to read file from local file system
            File file = new File(filename);
            InputStream stream;
            if (file.exists()) {
                stream = new FileInputStream(file);

            }else {
                stream = getClass().getClassLoader().getResourceAsStream(filename);
            }
            scanner = new Scanner(new BufferedInputStream(stream), Charset.defaultCharset().name());

        }
        catch (IOException e) {
            throw new IllegalArgumentException("Could not open " + filename, e);
        }
    }

    /**
     * Retorna true se essa stream tiver uma proxima linha.
     * Use essa metodo para sabe se é possivel chamar o {@link #readLine()} com sucesso.
     * @return {@code true} SE esse stream tem mais entradas (incluindo espaco em branco)
     *         {@code false} caso contrario
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Le e retorna o proxima linha de entrada desse stream.
     *
     * @return a próxima linha de entrada desse stream; {@code null} Se não existe linha
     */
    public String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        }
        catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }

    /**
     * Reads and returns the remainder of this input stream, as a string.
     *
     * @return the remainder of this input stream, as a string
     */
    public String readAll() {
        if (!scanner.hasNextLine())
            return "";

        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        // not that important to reset delimeter, since now scanner is empty
        scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
        return result;
    }


    @Override
    public void close() throws IOException {
        this.scanner.close();
    }

    public static void main(String[] args){
        In in = new In();
        while(in.hasNextLine()){
            String line = in.readLine();
            if( "".equals(line)){
                break;
            }
            System.out.println(line);
        }
    }
}
