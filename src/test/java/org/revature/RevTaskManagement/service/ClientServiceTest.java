package org.revature.RevTaskManagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.revature.RevTaskManagement.models.Client;
import org.revature.RevTaskManagement.repository.ClientRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testCreateClient() {
        Client client = new Client();
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.createClient(client);

        assertEquals(client, result);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void testGetAllClients() {
        List<Client> clients = Arrays.asList(new Client(), new Client());
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.getAllClients();

        assertEquals(clients, result);
        verify(clientRepository, times(1)).findAll();
    }
}
