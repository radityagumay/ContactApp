package net.radityalabs.contactapp;

import net.radityalabs.contactapp.data.network.RestService;
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase;
import net.radityalabs.contactapp.domain.usecase.ContactListUseCase;
import net.radityalabs.contactapp.presentation.listener.Callback;
import net.radityalabs.contactapp.presentation.presenter.ContactListPresenter;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactListPresenterTest {

    @Mock
    private ContactListUseCase mockUseCase;
    @Mock
    private List<ContactListResponse> contactListMock;
    @Mock
    private RestService mockService;
    @Mock
    private ContactListContract.View view;
    @Mock
    private Callback callback;

    private List<ContactListResponse> mContactListModel;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mContactListModel = generateModel();
    }

    @Test
    public void getContactList() {
        when(mockService.getContactList()).thenReturn(Single.just(generateModel()));

        mockUseCase = new ContactListUseCase(mockService);
        mockUseCase.getContactListTest(callback);

        verify(view).showContactList(generateModel());

        verify(callback, Mockito.times(1)).onSuccess(mContactListModel);
        verify(callback, never()).onFailure(any(Throwable.class));
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
            obj.firstName = FIRST_NAME[0];
            obj.lastName = LAST_NAME[0];
            obj.detailUrl = "https://www.radityalabs.net";
            obj.profilePic = "http://radityalabs.net/bootstrap/img/me.png";
            models.add(obj);
        }
        return models;
    }
}
