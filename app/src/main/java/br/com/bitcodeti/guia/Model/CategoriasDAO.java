//package br.com.bitcodeti.guia.Model;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.support.annotation.NonNull;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import br.com.legsassessoria.rl.Uteis.Strings;
//
///**
// * Created by Frederyk Antunnes on 31/05/2017.
// */
//
//public class CategoriasDAO extends SQLiteOpenHelper{
//    public static final String BASE_DADOS = Strings.BaseDeDados_nome;
//    public final String BASE_NOME_TABELA = "Usuarios";
//    public static final int VERSAO = Strings.BaseDeDados_versao;
//    public SQLiteDatabase db;
//
//    public static UsuarioVO usuarioLogado = new UsuarioVO();
//    public static UsuarioVO usuarioCadastrar = new UsuarioVO();
//
//
//    public static final String BD_USUARIO_CREATE_TABLE = "CREATE TABLE Usuarios (" +
//            "  idd INTEGER AUTO_INCREMENT," +
//            "  id INTEGER," +
//            "  nome TEXT NOT NULL," +
//            "  documento TEXT," +
//            "  CREF TEXT," +
//            "  dataNasc date," +
//            "  sexo TEXT," +
//            "  altura decimal(10,0)," +
//            "  peso decimal(10,0)," +
//            "  cidade TEXT," +
//            "  email TEXT NOT NULL," +
//            "  celular TEXT," +
//            "  login TEXT NOT NULL," +
//            "  senha TEXT NOT NULL," +
//            "  tipo INTEGER NOT NULL DEFAULT '1'," +
//            "  status INTEGER NOT NULL," +
//            "  idGrupo INTEGER NOT NULL," +
//            "  imagem TEXT," +
//            "  PRIMARY KEY (`idd`)" +
//            ")";
//
//    public CategoriasDAO(Context context) {
//        super(context, BASE_DADOS, null, VERSAO);
//
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(BD_USUARIO_CREATE_TABLE);
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS" +BASE_NOME_TABELA);
//        onCreate(db);
//
//    }
//
//    public void inserir(UsuarioVO usuario) {
//        db = getWritableDatabase();
//        ContentValues dados = getContentValues(usuario);
//        db.insert(BASE_NOME_TABELA, null, dados);
//    }
//
//    @NonNull
//    private ContentValues getContentValues(UsuarioVO usuario) {
//        ContentValues dados = new ContentValues();
//
//        dados.put("id", usuario.getId());
//        dados.put("nome", usuario.getNome());
//        dados.put("documento", usuario.getDocumento());
//        dados.put("CREF", usuario.getCREF());
//        dados.put("sexo", usuario.getSexo());
//        dados.put("altura", usuario.getAltura());
//        dados.put("peso", usuario.getPeso());
//        dados.put("cidade", usuario.getCidade());
//        dados.put("email", usuario.getEmail());
//        dados.put("celular", usuario.getCelular());
//        dados.put("login", usuario.getLogin());
//        dados.put("senha", usuario.getSenha());
//        dados.put("tipo", usuario.getTipo());
//        dados.put("status", usuario.getStatus());
//        dados.put("idGrupo", usuario.getIdGrupo());
//        dados.put("imagem", usuario.getImagem());
//        return dados;
//    }
//
//
//    public List<UsuarioVO> listarUsuarios() {
//        String sql = "SELECT * FROM "+BASE_NOME_TABELA+";";
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//        List<UsuarioVO> lista =  new ArrayList<UsuarioVO>();
//        while (c.moveToNext()){
//            UsuarioVO user = new UsuarioVO();
//            user.setIdd(c.getColumnIndex("idd"));
//            user.setId(c.getInt(c.getColumnIndex("id")));
//            user.setNome(c.getString(c.getColumnIndex("nome")));
//            user.setDocumento(c.getString(c.getColumnIndex("documento")));
//            user.setCREF(c.getString(c.getColumnIndex("CREF")));
//            user.setSexo(c.getString(c.getColumnIndex("sexo")));
//            user.setAltura(c.getFloat(c.getColumnIndex("altura")));
//            user.setPeso(c.getFloat(c.getColumnIndex("peso")));
//            user.setCidade(c.getString(c.getColumnIndex("cidade")));
//            user.setEmail(c.getString(c.getColumnIndex("email")));
//            user.setCelular(c.getString(c.getColumnIndex("celular")));
//            user.setLogin(c.getString(c.getColumnIndex("login")));
//            user.setSenha(c.getString(c.getColumnIndex("senha")));
//            user.setTipo(c.getInt(c.getColumnIndex("tipo")));
//            user.setStatus(c.getInt(c.getColumnIndex("status")));
//            user.setIdGrupo(c.getInt(c.getColumnIndex("idGrupo")));
//            user.setImagem(c.getString(c.getColumnIndex("imagem")));
//            lista.add(user);
//        }
//        c.close();
//        return lista;
//    }
//
//    public void remover(UsuarioVO user) {
//        db = getWritableDatabase();
//        String[]params = {""+user.getIdd()};
//        db.delete(BASE_NOME_TABELA, "idd=?", params);
//    }
//
//    public void alterar(UsuarioVO user) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues dados = getContentValues(user);
//        String[] params = {""+user.getId()};
//        db.update(BASE_NOME_TABELA, dados, "idd=?", params);
//    }
//
//    public void logout() {
//        db = getWritableDatabase();
//        db.execSQL("DROP TABLE IF EXISTS "+BASE_NOME_TABELA);
//        onCreate(db);
//        UsuarioDAO.usuarioLogado=null;
//    }
//
//    public static String converterUsuarioJSON(UsuarioVO user) throws JSONException {
//        JSONObject my_obj = new JSONObject();
//        my_obj.put("nome", user.getNome());
//        my_obj.put("sexo", user.getSexo());
//        my_obj.put("peso", user.getPeso());
//        my_obj.put("altura", user.getAltura());
//        my_obj.put("tipo", user.getTipo());
//        my_obj.put("cidade", user.getCidade());
//        my_obj.put("documento", user.getDocumento());
//        my_obj.put("celular", user.getCelular());
//        my_obj.put("email", user.getEmail());
//        my_obj.put("senha", user.getSenha());
//        my_obj.put("login", user.getLogin());
//        my_obj.put("id", user.getId());
//        String json_string = my_obj.toString();
//        return json_string;
//    }
//}
