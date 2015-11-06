/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.sample.domain.interactor;

import com.fernandocejas.android10.sample.data.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.data.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.interactor.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetUserDetailsTest {

	private static final int FAKE_USER_ID = 123;

	private GetUserDetails getUserDetails;

	@Mock
	private UserRepository mockUserRepository;
	@Mock
	private ThreadExecutor mockThreadExecutor;
	@Mock
	private PostExecutionThread mockPostExecutionThread;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		getUserDetails = new GetUserDetails(FAKE_USER_ID, mockUserRepository);
		getUserDetails.setThreadExecutor(mockThreadExecutor);
		getUserDetails.setPostExecutionThread(mockPostExecutionThread);

	}

	@Test
	public void testGetUserDetailsUseCaseObservableHappyCase() {
		getUserDetails.buildUseCaseObservable();

		verify(mockUserRepository).user(FAKE_USER_ID);
		verifyNoMoreInteractions(mockUserRepository);
		verifyZeroInteractions(mockPostExecutionThread);
		verifyZeroInteractions(mockThreadExecutor);
	}
}
