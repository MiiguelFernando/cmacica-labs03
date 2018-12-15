package pe.cmacica.labs.labs03.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pe.cmacica.labs.labs03.dominio.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class ClienteMapper implements RowMapper<Cliente>{

        @Override
        public Cliente mapRow(ResultSet rs, int i) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNombre(rs.getString("nombres"));
            cliente.setPaterno(rs.getString("paterno"));
            cliente.setMaterno(rs.getString("materno"));
            return cliente;
        }
    }

    @Override
    public List<Cliente> listar() {

        return jdbcTemplate.query("select id,nombres,paterno,materno from cliente", new ClienteMapper());

        /*List<Cliente> list = new ArrayList<>();
        for (int i=0; i<=10; i++){
            Cliente cli = new Cliente();
            cli.setId(i);
            cli.setNombre("Nombre " + i);
            list.add(cli);
        }

        return list;*/
    }

    @Override
    public Cliente listar(int id) {

        return jdbcTemplate.queryForObject("select id,nombres,paterno,materno from cliente where id=?",
                new Object[]{id},
                new ClienteMapper());

        /*List<Cliente> rs = jdbcTemplate.query("select * from cliente where id = " + id, new ClienteMapper());
        return rs.get(0);*/

       /* Cliente cli = new Cliente();
        cli.setId(id);
        cli.setNombre("Caldito de pollo");
        return cli;*/
    }

    @Override
    public int eliminar(int id) {
        return jdbcTemplate.update("delete from cliente where id=?", id);
    }

    @Override
    public int actualizar(Cliente cliente) {
        return jdbcTemplate.update("update cliente set nombres=?,paterno=?,materno=? where id=?",
                cliente.getNombre(), cliente.getPaterno(),cliente.getMaterno(), cliente.getId());
    }

    @Override
    public void insertar(Cliente cliente) {

        String SQL_INSERT = "INSERT INTO cliente(nombres,paterno,materno) values(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

                        ps.setString(1, cliente.getNombre());
                        ps.setString(2, cliente.getPaterno());
                        ps.setString(3, cliente.getMaterno());
                        return ps;
                    }
                }, keyHolder);

        int newUserId = keyHolder.getKey().intValue();
        cliente.setId(newUserId);
    }

    @Override
    public void abonarCuenta(String cuenta, double monto) {

    }

    @Override
    public void debitarCuenta(String cuenta, double monto) {

    }

}