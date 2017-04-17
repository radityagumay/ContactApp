package net.radityalabs.contactapp;

import net.radityalabs.contactapp.data.network.RestService;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.domain.usecase.ContactListUseCase;
import net.radityalabs.contactapp.presentation.listener.Callback;
import net.radityalabs.contactapp.presentation.presenter.ContactListPresenter;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

/**
 * Created by radityagumay on 4/17/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ContactListPresenterTest {

    @Mock
    private ContactListUseCase useCase;
    @Mock
    private List<ContactListResponse> contactListMock;
    @Mock
    private RestService service;
    @Mock
    private ContactListContract.View view;

    private ContactListPresenter presenter;
    private List<ContactListResponse> mContactListModel;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mContactListModel = generateModel();
    }

    @Test
    public void getContactList() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback) invocation.getArguments()[0]).onSuccess(mContactListModel);
                return null;
            }
        }).when(useCase).getContactListTest(any(Callback.class));
    }

    private static final String[] FIRST_NAME = new String[]{
            "Joko", "Nanda", "Saiful", "Kumbara", "Sasuke"
    };

    private static final String[] LAST_NAME = new String[]{
            "Uchiha", "Naruto", "Doraemon", "Santoso", "Umay"
    };

    private List<ContactListResponse> generateModel() {
        List<ContactListResponse> models = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            ContactListResponse obj = new ContactListResponse();
            obj.id = i;
            obj.isFavorite = true;
            obj.firstName = FIRST_NAME[new Random().nextInt(5)];
            obj.lastName = LAST_NAME[new Random().nextInt(5)];
            obj.detailUrl = "https://www.radityalabs.net";
            obj.profilePic = "http://radityalabs.net/bootstrap/img/me.png";
            models.add(obj);
        }
        return models;
    }
}
