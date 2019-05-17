package br.com.bitcodeti.guia.Uteis;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by frederykantunnes on 29/06/17.
 */

public class Web {

    public static String listarCategorias(){
        try {
            URL url = new URL(Strings.listarCateg);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.connect();
            Scanner scanner = new Scanner(connection.getInputStream());
            String resporta = scanner.nextLine();
            connection.disconnect();
            return resporta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String listarEventos(){
        try {
            URL url = new URL(Strings.listarEventos);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.connect();
            Scanner scanner = new Scanner(connection.getInputStream());
            String resporta = scanner.nextLine();
            connection.disconnect();
            return resporta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String listarLocais(){
        try {
            URL url = new URL(Strings.listarLoc);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.connect();
            Scanner scanner = new Scanner(connection.getInputStream());
            String resporta = scanner.nextLine();
            connection.disconnect();
            return resporta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String listarLocaisBusca(String busca){
        try {
            URL url = new URL(Strings.listarLocBusc+"?search="+busca);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.connect();
            Scanner scanner = new Scanner(connection.getInputStream());
            String resporta = scanner.nextLine();
            connection.disconnect();
            return resporta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


//    public String validarUsuario(String email, String senha){
//
//        try {
//            URL url = new URL(Strings.validarUsuario+"?email="+email+"&senha="+senha);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setDoOutput(false);
//            connection.setDoInput(true);
//            connection.connect();
//
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resposta = scanner.nextLine();
//            connection.disconnect();
//            return resposta;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//    public String cadastrarUsuario(String usuario_json){
//
//        try {
//            URL url = new URL(Strings.inserirUsuario);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.getOutputStream();
//            PrintStream output =  new PrintStream(connection.getOutputStream());
//            output.println(usuario_json);
//            connection.connect();
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public String alterarUsuario(String usuario_json){
//
//        try {
//            URL url = new URL(Strings.alterarUsuario);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.getOutputStream();
//            PrintStream output =  new PrintStream(connection.getOutputStream());
//            output.println(usuario_json);
//            connection.connect();
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



//    public String excluirConta(int id){
//
//        try {
//            URL url = new URL(Strings.excluirUsuario+"?id="+id);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//
//            connection.setDoOutput(false);
//            connection.setDoInput(true);
//
//            connection.connect();
//
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public String listarPessoasGrupo(int id){
//
//        try {
//            URL url = new URL(Strings.listarPessoasGrupo+"?id="+id);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//
//            connection.setDoOutput(false);
//            connection.setDoInput(true);
//
//            connection.connect();
//
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public String listarTreinosGrupo(int id){
//
//        try {
//            URL url = new URL(Strings.listarTreinosGrupo+"?id="+id);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//
//            connection.setDoOutput(false);
//            connection.setDoInput(true);
//
//            connection.connect();
//
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//    public String entrarNoGrupo(int id, String chave, String codigo){
//
//        try {
//            URL url = new URL(Strings.entrarNoGrupo+"?id="+id+"&&chave="+chave+"&&codigo="+codigo);
//            System.out.println(url);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setDoOutput(false);
//            connection.setDoInput(true);
//            connection.connect();
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public String listarDadosGrupo(int id){
//
//        try {
//            URL url = new URL(Strings.listarDadosGrupo+"?id="+id);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/text");
//
//            connection.setDoOutput(false);
//            connection.setDoInput(true);
//
//            connection.connect();
//
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public String listarEventosAgendados(int id){
//
//        try {
//            URL url = new URL(Strings.listarEventosAgendados+"?id="+id);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//
//            connection.setDoOutput(false);
//            connection.setDoInput(true);
//
//            connection.connect();
//
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public String sairDoGrupo(int id){
//
//        try {
//            URL url = new URL(Strings.sairDoGrupo+"?id="+id);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//
//            connection.setDoOutput(false);
//            connection.setDoInput(true);
//
//            connection.connect();
//
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//
//
//    public String participarEvento(int idUser, int idEvento){
//
//        try {
//            URL url = new URL(Strings.participarEvento+"?usuario="+idUser+"&&evento="+idEvento);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//
//            connection.setDoOutput(false);
//            connection.setDoInput(true);
//            connection.connect();
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public String deixarDeParticiparEvento(int idUser, int idEvento){
//
//        try {
//            URL url = new URL(Strings.deixarDeParticiparEvento+"?usuario="+idUser+"&&evento="+idEvento);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//
//            connection.setDoOutput(false);
//            connection.setDoInput(true);
//            connection.connect();
//            Scanner scanner = new Scanner(connection.getInputStream());
//            String resporta = scanner.nextLine();
//            connection.disconnect();
//            return resporta;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//
//





}
