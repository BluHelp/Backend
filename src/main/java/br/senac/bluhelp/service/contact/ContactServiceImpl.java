package br.senac.bluhelp.service.contact;



@Service
public class ContactServiceImpl implements ContactService {

	
	private final ContactRepository contactRepository;
	private final ContactMapper contactMapper;
	
	public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
		this.contactRepository = contactRepository;
		this.contactMapper = contactMapper;
		
		
	}

	@Override
	public void addContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeContact(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}
}
