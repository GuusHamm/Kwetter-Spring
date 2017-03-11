package nl.guushamm.service

import nl.guushamm.domain.Kweet
import nl.guushamm.repository.KweetRepository
import nl.guushamm.utils.testKweet
import nl.guushamm.utils.testKweets
import nl.guushamm.utils.testKweet
import nl.guushamm.utils.testKweets
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import java.util.*

/**
 * Created by guushamm on 8-3-17.
 */
class KweetServiceTest {
    val kweetService: KweetService by lazy { KweetServiceImpl() }
    val mockedKweetRepository: KweetRepository by lazy { mock(KweetRepository::class.java) }
    val kweets by lazy { ArrayList<Kweet>() }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        kweets.addAll(testKweets())
        kweetService.setKweetRepository(mockedKweetRepository)
    }

    @Test
    @Throws(Exception::class)
    fun findAll() {
        `when`(mockedKweetRepository.findAll()).thenReturn(kweets)
        val retrievedKweets = this.kweetService.findAll()

        verify(mockedKweetRepository).findAll()
        assert(retrievedKweets == kweets)
    }

    @Test
    @Throws(Exception::class)
    fun save() {
        val kweet: Kweet = testKweet()
        kweetService.save(kweet)

        verify(mockedKweetRepository).save(kweet)
    }

    @Test
    @Throws(Exception::class)
    fun findOne() {
        val kweet: Kweet = testKweet()
        val id: Long = 0
        `when`(mockedKweetRepository.findOne(id)).thenReturn(kweet)
        val returnedKweet: Kweet = kweetService.findOne(id)

        verify(mockedKweetRepository).findOne(id)
        assert(kweet == returnedKweet)
    }

    @Test
    @Throws(Exception::class)
    fun delete() {
        val id: Long = 0

        kweetService.delete(id)

        verify(mockedKweetRepository).delete(id)
    }
}