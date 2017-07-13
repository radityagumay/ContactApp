package net.radityalabs.contactapp

import net.radityalabs.contactapp.data.network.RestService
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase
import net.radityalabs.contactapp.domain.usecase.ContactListUseCase
import net.radityalabs.contactapp.presentation.listener.Callback
import net.radityalabs.contactapp.presentation.presenter.ContactListPresenter
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListContract

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer

import java.util.ArrayList
import java.util.Random

import io.reactivex.Single

import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * Created by radityagumay on 4/17/17.
 */

@RunWith(MockitoJUnitRunner::class)
class ContactListPresenterTest {

    @Mock
    private var mockUseCase: ContactListUseCase? = null
    @Mock
    private val contactListMock: List<ContactListResponse>? = null
    @Mock
    private val mockService: RestService? = null
    @Mock
    private val view: ContactListContract.View? = null
    @Mock
    private val callback: Callback<*>? = null

    private var mContactListModel: List<ContactListResponse>? = null

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mContactListModel = generateModel()
    }

    @Test
    fun getContactList() {
        `when`(mockService!!.contactList).thenReturn(Single.just(generateModel()))

        mockUseCase = ContactListUseCase(mockService)
        mockUseCase!!.getContactListTest(callback)

        verify<Callback>(callback, Mockito.times(1)).onSuccess(mContactListModel)
        verify<Callback>(callback, never()).onFailure(any<Throwable>(Throwable::class.java!!))
    }

    private fun generateModel(): List<ContactListResponse> {
        val models = ArrayList<ContactListResponse>(5)
        for (i in 0..4) {
            val obj = ContactListResponse()
            obj.id = i
            obj.isFavorite = true
            obj.firstName = FIRST_NAME[0]
            obj.lastName = LAST_NAME[0]
            obj.detailUrl = "https://www.radityalabs.net"
            obj.profilePic = "http://radityalabs.net/bootstrap/img/me.png"
            models.add(obj)
        }
        return models
    }

    companion object {

        private val FIRST_NAME = arrayOf("Joko", "Nanda", "Saiful", "Kumbara", "Sasuke")

        private val LAST_NAME = arrayOf("Uchiha", "Naruto", "Doraemon", "Santoso", "Umay")
    }
}
