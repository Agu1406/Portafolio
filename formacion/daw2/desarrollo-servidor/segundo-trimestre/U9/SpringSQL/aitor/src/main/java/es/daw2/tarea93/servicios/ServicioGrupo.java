package es.daw2.tarea91.servicios;
@Service
public class ServicioGrupo implements IFServicioGrupo {

    @Autowired
    repositorioGrupo repositorioGrupo;

    @Override
    public List<Grupo> listarGrupos() {
        return (List<Grupo>) repositorioGrupo.findAll();
    }

    @Override
    public Grupo anyadeGrupo(Grupo g) {
        return repositorio.save(g);
    }
}
