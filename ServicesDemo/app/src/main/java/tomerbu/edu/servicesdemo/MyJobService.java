package tomerbu.edu.servicesdemo;

import android.os.AsyncTask;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MyJobService extends JobService {
    private AsyncTask asyncTask;

    @Override
    public boolean onStartJob(final JobParameters job) {
        // Begin some async work
        asyncTask = new AsyncTask() {
            protected Object doInBackground(Object... objects) {
                /* do some work */
                Log.i("TomerBu", "Doing the job");
                return null;
            }

            protected void onPostExecute(Object result) {
                jobFinished(job, false /* no need to reschedule, we're done */);

            }
        };

        asyncTask.execute();

        return true; /* Still doing work */
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        asyncTask.cancel(true);

        return true; /* we're not done, please reschedule */
    }
}
