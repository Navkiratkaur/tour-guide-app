package com.example.android.tourfc;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class AttractionCollectionDataAdapter
		extends RecyclerView.Adapter<AttractionCollectionDataAdapter.SectionViewHolder> {

	/**
	 * Attractions data stored as an AttractionDetail object in an {@link ArrayList}
	 */
	private ArrayList<AttractionCollection> mDataset;

	private Context mContext;

	/**
	 * Creates custom RecyclerView.Adapter to manage AttractionDetails data object
	 *
	 * @param dataset an {@link ArrayList} of AttractionDetails data object
	 */
	AttractionCollectionDataAdapter(Context context, ArrayList<AttractionCollection> dataset) {
		this.mDataset = dataset;
		this.mContext = context;
	}

	@Override
	public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Inflate the layout outlined in card for individual row item and
		// Get the layout inflater from the parent view group,
		// which is parsed from R.layout.activity_main, and the inflated view is not attached to root
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.attraction_collection_layout, parent, false);
		// Return the inflated view unattached to root
		return new SectionViewHolder(view);
	}

	/**
	 * This method is called by RecyclerView to display the data at the specified position
	 *
	 * @param holder   the {@link SectionViewHolder} which is a member class is passed to set content
	 *                 for the child view group
	 * @param position the index position of the item in the adapter's data set
	 */
	@Override
	public void onBindViewHolder(SectionViewHolder holder, final int position) {
		// Get the data item that is being called to display
		AttractionCollection currentCollection = mDataset.get(position);

		// Get section header title from collection object
		final String SECTION_HEADER = mDataset.get(position).getHeaderTitle();

		// Get current attraction detail from current collection item
		final ArrayList<AttractionDetails> attractions = currentCollection.getCollection();

		SingleAttractionDataAdapter singleAttractionAdapter = new SingleAttractionDataAdapter(
				mContext, attractions);

		// Set text for section title
		holder.sectionTitle.setText(SECTION_HEADER);

		// Set matching text color for the section title and the secondary text "show all"
		switch (position) {
			case 0:
				holder.sectionTitle.setTextColor(
						ContextCompat
								.getColor(
										mContext,
										R.color.top_activities_text_color));
				holder.showAllClickable.setTextColor(
						ContextCompat
								.getColor(mContext,
										R.color.top_activities_text_color));
				break;

			case 1:
				holder.sectionTitle.setTextColor(
						ContextCompat
								.getColor(
										mContext,
										R.color.top_restaurants_text_color));
				holder.showAllClickable.setTextColor(
						ContextCompat
								.getColor(mContext,
										R.color.top_restaurants_text_color));
				break;

			case 2:
				holder.sectionTitle.setTextColor(
						ContextCompat
								.getColor(
										mContext,
										R.color.top_breweries_text_color));
				holder.showAllClickable.setTextColor(
						ContextCompat
								.getColor(mContext,
										R.color.top_breweries_text_color));
				break;

			case 3:
				holder.sectionTitle.setTextColor(
						ContextCompat
								.getColor(
										mContext,
										R.color.top_bars_nightlife_text_color));
				holder.showAllClickable.setTextColor(
						ContextCompat
								.getColor(mContext,
										R.color.top_bars_nightlife_text_color));
		}

		// Set fixed size true and optimize recycler view performance
		// The data container has fixed number of attractions and is not streaming from a web server
		holder.sectionCollectionRecyclerView.setHasFixedSize(true);

		// Set layout for the attractions collection data using a layout manager
		holder.sectionCollectionRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,
				LinearLayout.HORIZONTAL, false));

		// Connect attraction collection recycler view widget to corresponding data adapter
		holder.sectionCollectionRecyclerView.setAdapter(singleAttractionAdapter);
	}

	/**
	 * Returns the number of attractions in the attractions list
	 */
	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	/**
	 * This nested class is basically a member class that hooks into the child view group,
	 * providing the ability to set content for the views contained within the child view group
	 */
	static class SectionViewHolder extends RecyclerView.ViewHolder {


		TextView sectionTitle;

		RecyclerView sectionCollectionRecyclerView;

		TextView showAllClickable;

		LinearLayout linearLayout;

		final Context context;

		/**
		 * Create a {@link SectionViewHolder} that connects itself to all the relevant views
		 * contained within the child view group
		 *moulyg
		 * @param itemView a {@link ViewGroup} that is inflated when onCreateViewHolder is called
		 */
		SectionViewHolder(View itemView) {
			super(itemView);
			context = itemView.getContext();

			// Connect to the linear layout
			linearLayout = (LinearLayout) itemView.findViewById(R.id.section_linear_layout);

			// Connect to the image view of the child view group
			sectionTitle = (TextView) itemView.findViewById(R.id.section_title_text_view);

			// Connect to the text view of the child view group
			sectionCollectionRecyclerView = (RecyclerView) itemView
					.findViewById(R.id.section_collection_recycler_view);

			// Connect to the text view of the child view group
			showAllClickable = (TextView) itemView.findViewById(R.id.show_all_text_view);

			showAllClickable.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(context, "Show All is under works, to be up soon!",
							Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
}