/*
 * Copyright 2015 uaiHebert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
package test.com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.util.RequestHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URL;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RequestHolder.class)
public class RequestHolderTest {

    @Test(expected = IllegalStateException.class)
    public void isThrowingExceptionWhenThreadSleepRaisesError() throws NoSuchMethodException, InterruptedException {
        PowerMockito.spy(Thread.class);

        PowerMockito.doThrow(new InterruptedException()).when(Thread.class);
        Thread.sleep(Mockito.anyLong());

        final URL resource = UaiMockServerConfig.class.getResource("/performanceTest.config");
        UaiMockServerConfig uaiMockServerConfig = new UaiMockServerConfig(resource.getFile());

        RequestHolder.holdTheRequest(2L, uaiMockServerConfig);
    }
}