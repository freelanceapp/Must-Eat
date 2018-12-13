package infobite.must.eat.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.adapter.NotificationAdapter;
import infobite.must.eat.adapter.OffersAdapter;
import infobite.must.eat.modal.api_modal.notification_response.Notification;
import infobite.must.eat.modal.api_modal.notification_response.NotificationMainModel;
import infobite.must.eat.modal.api_modal.offer_response.OfferMainModal;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.ui.activities.OffersActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.BaseFragment;
import infobite.must.eat.utils.ConnectionDetector;
import retrofit2.Response;


public class NotificationFragment extends BaseFragment {
    public View view;
    RecyclerView notification_list;
    NotificationAdapter notificationAdapter;
    ArrayList<Notification> notificationArrayList = new ArrayList<>();
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_notification, container, false);
        notification_list = (RecyclerView)view.findViewById(R.id.notification_list);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();
        notificationApi();

        notificationAdapter = new NotificationAdapter(notificationArrayList, mContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        notification_list.setLayoutManager(mLayoutManager);
        notification_list.setItemAnimator(new DefaultItemAnimator());
        notification_list.setAdapter(notificationAdapter);
        return view;
    }
    private void notificationApi() {
        if (cd.isNetworkAvailable()) {

            RetrofitService.getNotificationList(new Dialog(mContext), retrofitApiClient.getNotificationList(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    NotificationMainModel notification = (NotificationMainModel) result.body();
                    assert notification != null;
                    notificationArrayList.clear();
                    if (!notification.getError()) {
                        Alerts.show(mContext, notification.getMessage());
                        notificationArrayList.addAll(notification.getNotification());

                        notificationAdapter.notifyDataSetChanged();

                    } else {
                        Alerts.show(mContext, notification.getMessage());
                            /*if (offerMainModal.getMessage().equals("User is Not Verified")) {
                               // startFragment(Constant.Verification_Fragment, new VerificationFragment(), loginModal.getUser().getPhone());
                                //activity.finish();
                            }*/
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });

        } else {
            cd.show(mContext);
        }
    }


}
