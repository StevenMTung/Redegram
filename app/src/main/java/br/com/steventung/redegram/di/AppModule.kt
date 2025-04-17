package br.com.steventung.redegram.di

import br.com.steventung.redegram.data.repository.PostRepository
import br.com.steventung.redegram.data.repository.PostRepositoryImpl
import br.com.steventung.redegram.data.samples.Samples
import br.com.steventung.redegram.mlkit.TextTranslate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideSamples(): Samples = Samples()

    @Singleton
    @Provides
    fun providePostRepository(samples: Samples): PostRepository {
        return PostRepositoryImpl(samples)
    }

    @Singleton
    @Provides
    fun provideTextTranslator(): TextTranslate {
        return TextTranslate()
    }

}

