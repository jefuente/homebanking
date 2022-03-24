package com.mindhub.homebanking;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class HomebankingApplication { //implements ApplicationRunner "probando metodo run"

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

}

/*
	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
*/




	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository,InvestRepository investRepository, ClientInvestRepository clientInvestRepository,
									   AccountRepository accountRepository,TransactionRepository transactionRepository,
									  LoanRepository loanRepository,ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {//ClientRepository clientRepository, AccountRepository accountRepository,TransactionRepository transactionRepository, LoanRepository loanRepository,ClientLoanRepository clientLoanRepository, CardRepository cardRepository
		return (args) -> {

			//Agregando nuevos clientres
			Client client1 =new Client("Juan", "Gutierrez","jgutierrez@mail.com",passwordEncoder.encode("123"),PerfilInversionType.CONSERVADOR);
			Client client2 = new Client("Julio", "Fuentes","jfuentes@mail.com",passwordEncoder.encode("123"),PerfilInversionType.CONSERVADOR);
			Client client3 = new Client("Josefina", "Toledo","jtoledo@mail.com",passwordEncoder.encode("123"),PerfilInversionType.CONSERVADOR);

		//	clientRepository.save(client1);
		//	clientRepository.save(client2);
		//	clientRepository.save(client3);

			Client client4 = new Client("Nicolas", "Nahuelvil","nnahuelvil@mail.com",passwordEncoder.encode("123"),PerfilInversionType.CONSERVADOR);
			Client client5 = new Client("Constanza", "Hesse","chesse@mail.com",passwordEncoder.encode("123"),PerfilInversionType.CONSERVADOR);

			//Guardando clientes
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);
			clientRepository.save(client4);
			clientRepository.save(client5);

			//Agregando nuevas cuentas asociadas al cliente

			//Cliente 1
			Account account1 = new Account("VIN001", LocalDateTime.now(),5000,client1);
			Account account2 = new Account("VIN002", LocalDateTime.now().plusDays(1),7500,client1);

			//Cliente 2
			Account account3 = new Account("VIN003", LocalDateTime.now(),1000,client2);
			Account account4 = new Account("VIN004", LocalDateTime.now().plusDays(1),5000,client2);

			//Cliente 3
			Account account5 = new Account("VIN005", LocalDateTime.now(),100000,client3);
			Account account6 = new Account("VIN006", LocalDateTime.now().plusDays(1),25000,client3);
			Account account7 = new Account("VIN007", LocalDateTime.now().plusDays(2),12000,client3);

			//Cliente 4
			Account account8 = new Account("VIN008", LocalDateTime.now(),9000,client4);

			//Cliente 5 sin cuentas

			//Guardando cuentas
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);
			accountRepository.save(account4);
			accountRepository.save(account5);
			accountRepository.save(account6);
			accountRepository.save(account7);
			accountRepository.save(account8);

			//Transacciones

			//Transaccion cuenta 1
			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 5000, "Transferencia 1", LocalDateTime.now(), account1);
			Transaction transaction2 = new Transaction(TransactionType.DEBIT, -2000, "Compra 1",LocalDateTime.now(),account1);

			//Transaccion cuenta 2
			Transaction transaction3 = new Transaction(TransactionType.CREDIT, 5000, "Transferencia 2", LocalDateTime.now(), account2);
			Transaction transaction4 = new Transaction(TransactionType.DEBIT, -43000, "Compra 2",LocalDateTime.now(),account2);

			//Transaccion cuenta 3
			Transaction transaction5 = new Transaction(TransactionType.CREDIT, 54000, "Transferencia 3", LocalDateTime.now(), account3);
			Transaction transaction6 = new Transaction(TransactionType.DEBIT, -5000, "Compra 3",LocalDateTime.now(),account3);

			//Transaccion cuenta 4
			Transaction transaction7 = new Transaction(TransactionType.CREDIT, 63000, "Transferencia 4", LocalDateTime.now(), account4);
			Transaction transaction8 = new Transaction(TransactionType.DEBIT, -8000, "Compra 4",LocalDateTime.now(),account4);

			//Transaccion cuenta 5
			Transaction transaction9 = new Transaction(TransactionType.CREDIT, 95000, "Transferencia 5", LocalDateTime.now(), account5);
			Transaction transaction10 = new Transaction(TransactionType.DEBIT, -4000, "Compra 5",LocalDateTime.now(),account5);

			//Transaccion cuenta 6
			Transaction transaction11 = new Transaction(TransactionType.CREDIT, 65000, "Transferencia 6", LocalDateTime.now(), account6);
			Transaction transaction12 = new Transaction(TransactionType.DEBIT, -47000, "Compra 6",LocalDateTime.now(),account6);

			//Transaccion cuenta 7
			Transaction transaction13 = new Transaction(TransactionType.CREDIT, 55000, "Transferencia 7", LocalDateTime.now(), account7);
			Transaction transaction14 = new Transaction(TransactionType.DEBIT, -84000, "Compra 7",LocalDateTime.now(),account7);

			//Transaccion cuenta 8
			Transaction transaction15= new Transaction(TransactionType.CREDIT, 55000, "Transferencia 8", LocalDateTime.now(), account8);
			Transaction transaction16 = new Transaction(TransactionType.DEBIT, -49000, "Compra 8",LocalDateTime.now(),account8);

			//Guardar transaccion

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);
			transactionRepository.save(transaction7);
			transactionRepository.save(transaction8);
			transactionRepository.save(transaction9);
			transactionRepository.save(transaction10);
			transactionRepository.save(transaction11);
			transactionRepository.save(transaction12);
			transactionRepository.save(transaction13);
			transactionRepository.save(transaction14);
			transactionRepository.save(transaction15);
			transactionRepository.save(transaction16);

			///agregar prestamos

			Loan loan1 = new Loan("Hipotecario", 500000, List.of(12,24,38,48,60));
			Loan loan2 = new Loan("Personal", 100000, List.of(6,12,24));
			Loan loan3 = new Loan("Automotriz", 300000, List.of(6,12,24,36));

			//Guardar prestamos
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			//Crear prestamos de clientes
			ClientLoan clientLoan1 = new ClientLoan(client1,loan1,60,4000000);
			ClientLoan clientLoan2 = new ClientLoan(client1,loan2,24,50000);
			ClientLoan clientLoan3 = new ClientLoan(client2,loan2,24,100000);
			ClientLoan clientLoan4 = new ClientLoan(client2,loan3,36,200000);

			//Guardar prestamos de clientes
			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);




			//Crenado tarjetas de credito para el cliente uno  y dos
			//cliente 1
			Card card1 = new Card(client1.getFirstName()+" "+client1.getLastName(), CardType.DEBIT,CardColor.GOLD,"3325-6745-7876-4445",990,LocalDateTime.now(),LocalDateTime.now().plusYears(5),client1);
			Card card2 = new Card(client1.getFirstName()+" "+client1.getLastName(), CardType.CREDIT,CardColor.TITANIUM,"2234-6745-5522-7888",750,LocalDateTime.now(),LocalDateTime.now().plusYears(5),client1);
			//cliente 2
			Card card3 = new Card(client2.getFirstName()+" "+client2.getLastName(), CardType.CREDIT,CardColor.SILVER,"5411-3819-7365-3039",313,LocalDateTime.now(),LocalDateTime.now().plusYears(5),client2);

			//Guardamos la tarjeta
			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);




			// Agregar productos inversion



			Invest invest1 = new Invest("Fondo Riesgo 1", 3, RiskType.CONSERVADOR, Arrays.asList(1, 2, 3, 4) );
			Invest invest2 = new Invest("Fondo Riesgo 2", 3, RiskType.AUDAZ,Arrays.asList(1, 2, 3, 4) );
			Invest invest3 = new Invest("Fondo Riesgo 3", 5, RiskType.CRECIMIENTO,Arrays.asList(1, 2, 3, 4) );
			Invest invest4 = new Invest("Fondo Riesgo 4", 5, RiskType.DECIDIDO,Arrays.asList(1, 2, 3, 4) );
			Invest invest5 = new Invest("Fondo Riesgo 5", 7, RiskType.MODERADO,Arrays.asList(1, 2, 3, 4) );
			Invest invest6 = new Invest("Fondo Riesgo 6", 10, RiskType.ESPECULATIVO,Arrays.asList(1, 2, 3, 4) );



			//Guardar productos inversion
			investRepository.save(invest1);
			investRepository.save(invest2);
			investRepository.save(invest3);
			investRepository.save(invest4);
			investRepository.save(invest5);
			investRepository.save(invest6);


			//Crear inversiones de cliente
			ClientInvest clientInvest1= new ClientInvest(5000, 2,client1, invest1,true);
			ClientInvest clientInvest2= new ClientInvest(100000, 1,client2, invest2,true);
			ClientInvest clientInvest3= new ClientInvest(50000, 3,client3, invest3,true);
			ClientInvest clientInvest4= new ClientInvest(300000, 3,client1, invest4,true);
			ClientInvest clientInvest5= new ClientInvest(150000, 2,client2, invest5,true);
			ClientInvest clientInvest6= new ClientInvest(50000, 1,client3, invest6,true);
			ClientInvest clientInvest7= new ClientInvest(200000, 4,client1, invest5,true);
			ClientInvest clientInvest8= new ClientInvest(150000, 3,client2, invest3,true);
			ClientInvest clientInvest9= new ClientInvest(250000, 4,client3, invest1,true);

			//Guardar inversiones de cliente
			clientInvestRepository.save(clientInvest1);
			clientInvestRepository.save(clientInvest2);
			clientInvestRepository.save(clientInvest3);
			clientInvestRepository.save(clientInvest4);
			clientInvestRepository.save(clientInvest5);
			clientInvestRepository.save(clientInvest6);
			clientInvestRepository.save(clientInvest7);
			clientInvestRepository.save(clientInvest8);
			clientInvestRepository.save(clientInvest9);

		};
	}

}

/*
//Prueba metodo run

	/*
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AccountRepository accountRepository;
	*/

	/*
	@Override
	public void run(ApplicationArguments args) throws Exception {

		Client client =new Client("Melba", "Lorenzo","mlorenzo@mail.com");
		Client client1 = new Client("Felipe", "Negrete","fnegrete@mail.com");

		clientRepository.save(client);
		clientRepository.save(client1);

		Account account1 = new Account("VIN001", LocalDateTime.now(),5000,client);
		Account account2 = new Account("VIN002", LocalDateTime.now().plusDays(1),7500,client);

		Account account3 = new Account("VIN003", LocalDateTime.now(),1000,client1);
		Account account4 = new Account("VIN004", LocalDateTime.now(),5000,client1);

		accountRepository.save(account1);
		accountRepository.save(account2);
		accountRepository.save(account3);
		accountRepository.save(account4);
	}
	 */

