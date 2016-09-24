package br.com.elotech.karina.repository.jdbc;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.elotech.karina.domain.IntegracaoLicenca;
import br.com.elotech.karina.repository.IntegracaoLicencaRepository;

@Component
public class IntegracaoLicencaRepositoryJdbc implements IntegracaoLicencaRepository {

    private final JdbcTemplate jdbcTemplate;

    public IntegracaoLicencaRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<IntegracaoLicenca> findNaoProcessados() {

        final String sql = "select a.usu_codemp as CodigoEmpresa,"
                + " a.usu_codfil as CodigoFilial,"
                + " a.usu_numctr as NumeroInternoContrato,"
                + " a.usu_seqitg as SequenciaIntegracao,"
                + " a.usu_seqcvs as SequenciaItemServico,"
                + " a.usu_codcli as CodigoClienteVinculadoContrato,"
                + " a.usu_nomcli as NomeCliente,"
                + " a.usu_codser as CodigoServico,"
                + " a.usu_sitctr as SituacaoContrato,"
                + " a.usu_sitcvs as SituacaoServico,"
                + " a.usu_codrep as CodigoRepresentante,"
                + " a.usu_numtit as NumeroTitulo,"
                + " a.usu_codtpt as TipoTitulo,"
                + " a.usu_seqmov as SequenciaMovimentacaoTitulo,"
                + " a.usu_emitit as EmissaoTitulo,"
                + " a.usu_vcttit as VencimentoTitulo,"
                + " a.usu_baitit as QuitacaoTitulo,"
                + " a.usu_cptctr as DataCompetenciaServicoContrato,"
                + " a.usu_datger as DataGeracaoRepositorio,"
                + " a.usu_horger as HoraGeracaoRepositorio,"
                + " a.usu_usuger as UsuarioGeracaRepositorio,"
                + " a.usu_tipger as TipoGeracaoRepositorio,"
                + " a.usu_blolib as TipoRegistro,"
                + " a.usu_codmot as MotivoServicoContrato,"
                + " a.usu_obsitg as Observacao,"
                + " a.usu_tipaca as TipoAcao"
                + " from usu_t160itg a";

        return this.jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<IntegracaoLicenca>(IntegracaoLicenca.class));
    }

    @Override
    public void marcaTodosProcessados() {
        // TODO Auto-generated method stub

    }

}
