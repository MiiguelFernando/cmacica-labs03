package pe.cmacica.labs.labs03.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pe.cmacica.labs.labs03.dominio.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            return cliente;
        }
    }

    @Override
    public List<Cliente> listar() {

        return jdbcTemplate.query("select * from cliente", new ClienteMapper());

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

        return jdbcTemplate.queryForObject("select * from cliente where id=?",
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
        return jdbcTemplate.update("update cliente set nombres=? where id=?",
                cliente.getNombre(), cliente.getId());
    }

}
