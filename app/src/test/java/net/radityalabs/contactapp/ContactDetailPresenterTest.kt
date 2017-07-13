package net.radityalabs.contactapp

import android.content.Context

import net.radityalabs.contactapp.data.network.RestService
import net.radityalabs.contactapp.data.network.RetrofitHelper
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.data.realm.RealmHelper
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase
import net.radityalabs.contactapp.presentation.listener.Callback
import net.radityalabs.contactapp.presentation.presenter.contract.ContactDetailContract

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

import java.util.Random

import io.reactivex.Single

import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * Created by radityagumay on 4/19/17.
 */

@RunWith(MockitoJUnitRunner::class)
class ContactDetailPresenterTest {

    @Mock
    private val mockContext: Context? = null
    @Mock
    private val mockRealm: RealmHelper? = null
    @Mock
    private val mockRetrofit: RetrofitHelper? = null
    @Mock
    private val mockService: RestService? = null
    @Mock
    private var mockUseCase: ContactDetailUseCase? = null
    @Mock
    private val mockView: ContactDetailContract.View? = null
    @Mock
    private val callback: Callback<*>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun fetch_valid_data_should_load_into_view() {
        val obj = ContactDetailResponse()
        obj.id = 1
        obj.isFavorite = true
        obj.firstName = FIRST_NAME[Random().nextInt(5)]
        obj.lastName = LAST_NAME[Random().nextInt(5)]
        obj.email = "adit@yahoo.com"
        obj.profilePic = "http://radityalabs.net/bootstrap/img/me.png"

        `when`(mockService!!.getContactDetail(1)).thenReturn(Single.just(obj))

        mockUseCase = ContactDetailUseCase(mockService)
        mockUseCase!!.getDetailContactTest(1, callback)

        verify<Callback>(callback, Mockito.times(1)).onSuccess(any<ContactDetailResponse>(ContactDetailResponse::class.java!!))
        verify<Callback>(callback, never()).onFailure(any<Throwable>(Throwable::class.java!!))
    }

    companion object {

        private val FIRST_NAME = arrayOf("Joko", "Nanda", "Saiful", "Kumbara", "Sasuke")

        private val LAST_NAME = arrayOf("Uchiha", "Naruto", "Doraemon", "Santoso", "Umay")
    }
}
