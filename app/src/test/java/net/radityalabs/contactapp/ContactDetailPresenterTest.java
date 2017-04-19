package net.radityalabs.contactapp;

import net.radityalabs.contactapp.data.network.RestService;
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactDetailContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import io.reactivex.Flowable;
import io.reactivex.Single;

import static org.mockito.Mockito.when;

/**
 * Created by radityagumay on 4/19/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ContactDetailPresenterTest {

    @Mock
    private RestService mockService;
    @Mock
    private ContactDetailUseCase mockUseCase;
    @Mock
    private ContactDetailContract.View mockView;

    private static final String[] FIRST_NAME = new String[]{
            "Joko", "Nanda", "Saiful", "Kumbara", "Sasuke"
    };

    private static final String[] LAST_NAME = new String[]{
            "Uchiha", "Naruto", "Doraemon", "Santoso", "Umay"
    };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetch_valid_data_should_load_into_view() {
        ContactDetailResponse obj = new ContactDetailResponse();
        obj.id = 1;
        obj.isFavorite = true;
        obj.firstName = FIRST_NAME[new Random().nextInt(5)];
        obj.lastName = LAST_NAME[new Random().nextInt(5)];
        obj.email = "adit@yahoo.com";
        obj.profilePic = "http://radityalabs.net/bootstrap/img/me.png";

        when(mockService.getContactDetail(1)).thenReturn(Single.just(obj));
    }
}
