/*
 * Copyright (C) 2017 The Android Open Source Project
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
 */

package com.android.settings.applications;

import android.annotation.NonNull;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.UserHandle;

import com.android.internal.util.Preconditions;
import com.android.settings.utils.AsyncLoader;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.applications.StorageStatsSource.AppStorageStats;

/**
 * Fetches the storage stats using the StorageStatsManager for a given package and user tuple.
 */
public class FetchPackageStorageAsyncLoader extends AsyncLoader<AppStorageStats> {
    private final StorageStatsSource mSource;
    private final ApplicationInfo mInfo;
    private final UserHandle mUser;

    public FetchPackageStorageAsyncLoader(Context context, @NonNull StorageStatsSource source,
            @NonNull ApplicationInfo info, @NonNull UserHandle user) {
        super(context);
        mSource = Preconditions.checkNotNull(source);
        mInfo = info;
        mUser = user;
    }

    @Override
    public AppStorageStats loadInBackground() {
        return mSource.getStatsForPackage(mInfo.volumeUuid, mInfo.packageName, mUser);
    }

    @Override
    protected void onDiscardResult(AppStorageStats result) {
    }
}
