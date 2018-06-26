package DAOs;


import Entidades.PrecoEstimado;
import DAOs.DAOGenerico;
import static DAOs.DAOGenerico.em;
import java.util.ArrayList;
import java.util.List;

public class DAOPrecoEstimado extends DAOGenerico<PrecoEstimado> {

    public DAOPrecoEstimado() {
        super(PrecoEstimado.class);
    }

    public int autoIdPrecoEstimado() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPrecoEstimado) FROM PrecoEstimado e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<PrecoEstimado> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PrecoEstimado e WHERE e.data LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PrecoEstimado> listById(int id) {
        return em.createQuery("SELECT e FROM PrecoEstimado e WHERE e.floresIdFlor = :id").setParameter("id", id).getResultList();
    }

    public List<PrecoEstimado> listInOrderNome() {
        return em.createQuery("SELECT e FROM PrecoEstimado e ORDER BY e.data").getResultList();
    }

    public List<PrecoEstimado> listInOrderId() {
        return em.createQuery("SELECT e FROM PrecoEstimado e ORDER BY e.floresIdFlor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<PrecoEstimado> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getFlores().getIdFlor() + "-" + lf.get(i).get());
        }
        return ls;
    }
public static void main(String[] args) {
        DAOPrecoEstimado daoPrecoEstimado = new DAOPrecoEstimado();
        List<PrecoEstimado> listaPrecoEstimado = daoPrecoEstimado.list();
        for (PrecoEstimado precoEstimado : listaPrecoEstimado) {
            System.out.println(precoEstimado.getFloresIdFlor() + "-" + precoEstimado.getData());
        }
    }}
