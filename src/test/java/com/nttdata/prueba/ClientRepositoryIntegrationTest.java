package com.nttdata.prueba;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.nttdata.prueba.model.entities.Client;
import com.nttdata.prueba.model.enums.GenderType;
import com.nttdata.prueba.repository.ClientRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class ClientRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;
	
    @Test
	public void status() {
    	assertTrue(true);
    }
	@Test
	public void whenFindByName_thenReturnClient() {
	    // given
	    Client client = new Client();
	    client.setAddress("conocoto");
	    client.setAge(23);
	    client.setGender(GenderType.MASCULINO);
	    client.setName("Galo");
	    client.setPhone("09927298");
	    client.setStatus(true);
	    entityManager.persist(client);
	    entityManager.flush();

	    // when
	    Client found = clientRepository.findByName(client.getName()).get();

	    // then
	    assertThat(found.getName())
	      .isEqualTo(client.getName());
	}

}
