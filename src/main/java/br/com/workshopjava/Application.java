package br.com.workshopjava;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.workshopjava.domain.Categoria;
import br.com.workshopjava.domain.Cidade;
import br.com.workshopjava.domain.Cliente;
import br.com.workshopjava.domain.Endereco;
import br.com.workshopjava.domain.Estado;
import br.com.workshopjava.domain.Pagamento;
import br.com.workshopjava.domain.PagamentoComBoleto;
import br.com.workshopjava.domain.PagamentoComCartao;
import br.com.workshopjava.domain.Pedido;
import br.com.workshopjava.domain.Produto;
import br.com.workshopjava.domain.enums.StatusPagamento;
import br.com.workshopjava.domain.enums.TipoCliente;
import br.com.workshopjava.repository.CategoriaRepository;
import br.com.workshopjava.repository.CidadeRepository;
import br.com.workshopjava.repository.ClienteRepository;
import br.com.workshopjava.repository.EnderecoRepository;
import br.com.workshopjava.repository.EstadoRepository;
import br.com.workshopjava.repository.PagamentoRepository;
import br.com.workshopjava.repository.PedidoRepository;
import br.com.workshopjava.repository.ProdutoRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepositoryo;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		Produto p1 = new Produto(null, "Computador", new BigDecimal("2000.00"));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal("800.00"));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal("80.00"));

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));


		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "938383964"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "APTO 305", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, StatusPagamento.QUITADO, ped1, 6);	
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, StatusPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepositoryo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
