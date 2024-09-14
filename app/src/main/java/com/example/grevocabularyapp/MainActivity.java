package com.example.grevocabularyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.wordRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Prepare word data
        List<Word> wordList = new ArrayList<>();
        wordList.add(new Word("Aberration", "A departure from what is normal"));
        wordList.add(new Word("Capitulate", "Cease to resist an opponent"));
        wordList.add(new Word("Debilitate", "To weaken"));
        wordList.add(new Word("Ebullient", "Cheerful and full of energy"));
        wordList.add(new Word("Facetious", "Treating serious issues with inappropriate humor"));
        wordList.add(new Word("Garrulous", "Excessively talkative"));
        wordList.add(new Word("Hapless", "Unfortunate"));
        wordList.add(new Word("Idiosyncratic", "Peculiar or individual"));
        wordList.add(new Word("Juxtapose", "To place side by side for contrast"));
        wordList.add(new Word("Keen", "Highly developed or sharp"));
        wordList.add(new Word("Lackadaisical", "Lacking enthusiasm and determination"));
        wordList.add(new Word("Mollify", "To appease the anger or anxiety of someone"));
        wordList.add(new Word("Nefarious", "Wicked or criminal"));
        wordList.add(new Word("Obfuscate", "To render obscure, unclear, or unintelligible"));
        wordList.add(new Word("Perfunctory", "Carried out with minimum effort or reflection"));
        wordList.add(new Word("Quixotic", "Exceedingly idealistic; unrealistic and impractical"));
        wordList.add(new Word("Resplendent", "Attractive and impressive through being richly colorful or sumptuous"));
        wordList.add(new Word("Sycophant", "A person who acts obsequiously toward someone important to gain advantage"));
        wordList.add(new Word("Trepidation", "A feeling of fear or agitation about something that may happen"));
        wordList.add(new Word("Ubiquitous", "Present, appearing, or found everywhere"));
        wordList.add(new Word("Venerate", "To regard with great respect"));
        wordList.add(new Word("Wistful", "Having or showing a feeling of vague or regretful longing"));
        wordList.add(new Word("Xenophobia", "Dislike of or prejudice against people from other countries"));
        wordList.add(new Word("Yield", "To produce or provide something"));
        wordList.add(new Word("Zealot", "A person who is fanatical and uncompromising in pursuit of their ideals"));

        wordList.add(new Word("Aplomb", "Self-confidence or assurance, especially when in a demanding situation"));
        wordList.add(new Word("Bellicose", "Demonstrating aggression and willingness to fight"));
        wordList.add(new Word("Cacophony", "A harsh, discordant mixture of sounds"));
        wordList.add(new Word("Dichotomy", "A division or contrast between two things that are represented as being opposed or entirely different"));
        wordList.add(new Word("Eloquent", "Fluent or persuasive in speaking or writing"));
        wordList.add(new Word("Flabbergasted", "Extremely surprised or shocked"));
        wordList.add(new Word("Gregarious", "Fond of company; sociable"));
        wordList.add(new Word("Hapless", "Unfortunate or unlucky"));
        wordList.add(new Word("Iconoclast", "A person who attacks or criticizes cherished beliefs or institutions"));
        wordList.add(new Word("Juxtaposition", "The fact of two things being seen or placed close together with contrasting effect"));
        wordList.add(new Word("Kaleidoscope", "A constantly changing pattern of shapes and colors"));
        wordList.add(new Word("Lethargic", "Affected by lethargy; sluggish and apathetic"));
        wordList.add(new Word("Munificent", "More generous than is usual or necessary"));
        wordList.add(new Word("Nostalgia", "A sentimental longing or wistful affection for the past"));
        wordList.add(new Word("Ominous", "Giving the impression that something bad or unpleasant is going to happen; threatening"));
        wordList.add(new Word("Pernicious", "Having a harmful effect, especially in a gradual or subtle way"));
        wordList.add(new Word("Quintessential", "Representing the most perfect or typical example of a quality or class"));
        wordList.add(new Word("Recalcitrant", "Having an obstinately uncooperative attitude towards authority or discipline"));
        wordList.add(new Word("Sanguine", "Optimistic or positive, especially in an apparently bad or difficult situation"));
        wordList.add(new Word("Tantamount", "Equivalent in seriousness to; virtually the same as"));
        wordList.add(new Word("Unequivocal", "Leaving no doubt; unambiguous"));
        wordList.add(new Word("Voracious", "Wanting or devouring great quantities of food; having a very eager approach to a particular activity"));
        wordList.add(new Word("Wary", "Feeling or showing caution about possible dangers or problems"));
        wordList.add(new Word("Xenial", "Friendly relations between host and guest"));
        wordList.add(new Word("Yielding", "Giving way under pressure; flexible"));
        wordList.add(new Word("Zealous", "Having or showing zeal; fervent or enthusiastic"));

        wordList.add(new Word("Aesthetic", "Concerned with beauty or the appreciation of beauty"));
        wordList.add(new Word("Alacrity", "Brisk and cheerful readiness"));
        wordList.add(new Word("Ambiguous", "Open to more than one interpretation; not having one obvious meaning"));
        wordList.add(new Word("Apathetic", "Showing or feeling no interest, enthusiasm, or concern"));
        wordList.add(new Word("Arcane", "Known or understood by very few; mysterious or secret"));
        wordList.add(new Word("Banal", "So lacking in originality as to be obvious and boring"));
        wordList.add(new Word("Benign", "Gentle and kindly; not harmful in effect"));
        wordList.add(new Word("Boisterous", "Noisy, energetic, and cheerful"));
        wordList.add(new Word("Capricious", "Given to sudden and unaccountable changes of mood or behavior"));
        wordList.add(new Word("Censure", "Express severe disapproval of (someone or something), typically in a formal statement"));
        wordList.add(new Word("Clever", "Quick to understand, learn, and devise or apply ideas; intelligent"));
        wordList.add(new Word("Convoluted", "Extremely complex and difficult to follow"));
        wordList.add(new Word("Cryptic", "Having a meaning that is mysterious or obscure"));
        wordList.add(new Word("Deficient", "Lacking in some necessary quality or element"));
        wordList.add(new Word("Deferential", "Showing respect and high regard; yielding to the judgment or opinion of others"));
        wordList.add(new Word("Diligent", "Having or showing care and conscientiousness in one's work or duties"));
        wordList.add(new Word("Ebullient", "Cheerful and full of energy"));
        wordList.add(new Word("Eloquent", "Fluent or persuasive in speaking or writing"));
        wordList.add(new Word("Enervate", "To cause (someone) to feel drained of energy or vitality; to weaken"));
        wordList.add(new Word("Equivocal", "Open to more than one interpretation; ambiguous"));
        wordList.add(new Word("Exacerbate", "To make a problem, bad situation, or negative feeling worse"));
        wordList.add(new Word("Exemplary", "Serving as a desirable model; representing the best of its kind"));
        wordList.add(new Word("Facetious", "Treating serious issues with deliberately inappropriate humor"));
        wordList.add(new Word("Feasible", "Possible to do easily or conveniently"));
        wordList.add(new Word("Frivolous", "Not having any serious purpose or value; carefree and superficial"));
        wordList.add(new Word("Gregarious", "Fond of company; sociable"));
        wordList.add(new Word("Harbinger", "A person or thing that announces or signals the approach of another"));
        wordList.add(new Word("Impetuous", "Acting or done quickly and without thought or care"));
        wordList.add(new Word("Incredulous", "Unwilling or unable to believe something"));
        wordList.add(new Word("Indolent", "Wanting to avoid activity or exertion; lazy"));
        wordList.add(new Word("Ineffable", "Too great or extreme to be expressed or described in words"));
        wordList.add(new Word("Inimical", "Hostile; unfriendly"));
        wordList.add(new Word("Insidious", "Proceeding in a subtle way but with harmful effects"));
        wordList.add(new Word("Lethargic", "Affected by lethargy; sluggish and apathetic"));
        wordList.add(new Word("Mellifluous", "Sweet or musical; pleasant to hear"));
        wordList.add(new Word("Meticulous", "Showing great attention to detail; very careful and precise"));
        wordList.add(new Word("Mitigate", "To make less severe, serious, or painful"));
        wordList.add(new Word("Nonchalant", "Feeling or appearing casually calm and relaxed; not displaying anxiety, interest, or enthusiasm"));
        wordList.add(new Word("Obfuscate", "To deliberately make something difficult to understand"));
        wordList.add(new Word("Paucity", "The presence of something in only small or insufficient quantities or amounts"));
        wordList.add(new Word("Perpetuate", "To make (something, typically an undesirable situation or an unfounded belief) continue indefinitely"));
        wordList.add(new Word("Pliable", "Easily bent; flexible"));
        wordList.add(new Word("Prosaic", "Lacking poetic beauty; commonplace or dull"));
        wordList.add(new Word("Quintessential", "Representing the most perfect or typical example of a quality or class"));
        wordList.add(new Word("Recalcitrant", "Having an obstinately uncooperative attitude towards authority or discipline"));
        wordList.add(new Word("Resilient", "Able to withstand or recover quickly from difficult conditions"));
        wordList.add(new Word("Sanguine", "Optimistic or positive, especially in an apparently bad or difficult situation"));
        wordList.add(new Word("Skeptical", "Not easily convinced; having doubts or reservations"));
        wordList.add(new Word("Soporific", "Tending to induce drowsiness or sleepiness"));
        wordList.add(new Word("Superfluous", "Unnecessary, especially through being more than enough"));
        wordList.add(new Word("Transient", "Lasting only for a short time; impermanent"));
        wordList.add(new Word("Ubiquitous", "Present, appearing, or found everywhere"));
        wordList.add(new Word("Venerable", "Accorded a great deal of respect, especially because of age, wisdom, or character"));
        wordList.add(new Word("Voracious", "Wanting or devouring great quantities of food; having a very eager approach to a particular activity"));
        wordList.add(new Word("Wary", "Feeling or showing caution about possible dangers or problems"));
        wordList.add(new Word("Wistful", "Having or showing a feeling of vague or regretful longing"));
        wordList.add(new Word("Zealous", "Having or showing zeal; fervent or enthusiastic"));

        wordList.add(new Word("Adroit", "Clever or skillful in using the hands or mind"));
        wordList.add(new Word("Aesthetic", "Concerned with beauty or the appreciation of beauty"));
        wordList.add(new Word("Banal", "So lacking in originality as to be obvious and boring"));
        wordList.add(new Word("Belligerent", "Hostile and aggressive"));
        wordList.add(new Word("Boisterous", "Noisy, energetic, and cheerful"));
        wordList.add(new Word("Candid", "Truthful and straightforward; frank"));
        wordList.add(new Word("Cumbersome", "Large or heavy and therefore difficult to carry or use; unwieldy"));
        wordList.add(new Word("Desolate", "Empty and bleak; abandoned"));
        wordList.add(new Word("Eccentric", "Unconventional and slightly strange"));
        wordList.add(new Word("Enigma", "A person or thing that is mysterious, puzzling, or difficult to understand"));
        wordList.add(new Word("Flamboyant", "Tending to attract attention because of their exuberance, confidence, and stylishness"));
        wordList.add(new Word("Grim", "Forbidding or uninviting; stern"));
        wordList.add(new Word("Haphazard", "Lacking any obvious principle of organization"));
        wordList.add(new Word("Impudent", "Not showing due respect for another person; impertinent"));
        wordList.add(new Word("Inane", "Silly or stupid"));
        wordList.add(new Word("Juxtapose", "To place close together or side by side"));
        wordList.add(new Word("Luminous", "Full of or shedding light; bright or shining, especially in the dark"));
        wordList.add(new Word("Metamorphosis", "A change of the form or nature of a thing or person into a completely different one"));
        wordList.add(new Word("Nefarious", "Wicked or criminal"));
        wordList.add(new Word("Oblivious", "Not aware of or not concerned about what is happening around one"));
        wordList.add(new Word("Pernicious", "Having a harmful effect, especially in a gradual or subtle way"));
        wordList.add(new Word("Quintessential", "Representing the most perfect or typical example of a quality or class"));
        wordList.add(new Word("Rejuvenate", "To make someone or something look or feel better, younger, or more vital"));
        wordList.add(new Word("Scrupulous", "Diligent, thorough, and extremely attentive to details"));
        wordList.add(new Word("Tranquil", "Free from disturbance; calm"));
        wordList.add(new Word("Uncanny", "Strange or mysterious, especially in an unsettling way"));
        wordList.add(new Word("Vehement", "Showing strong feeling; forceful, passionate, or intense"));
        wordList.add(new Word("Voracious", "Wanting or devouring great quantities of food; having a very eager approach to a particular activity"));
        wordList.add(new Word("Wistful", "Longing or yearning for something in the past"));
        wordList.add(new Word("Zealot", "A person who is fanatical and uncompromising in pursuit of their ideals"));

        wordList.add(new Word("Aplomb", "Self-confidence or assurance, especially when in a demanding situation"));
        wordList.add(new Word("Belligerent", "Hostile and aggressive"));
        wordList.add(new Word("Charismatic", "Exercising a compelling charm which inspires devotion in others"));
        wordList.add(new Word("Dissonant", "Lacking harmony"));
        wordList.add(new Word("Eloquent", "Fluent or persuasive in speaking or writing"));
        wordList.add(new Word("Fervent", "Having or displaying a passionate intensity"));
        wordList.add(new Word("Gratuitous", "Uncalled for; lacking good reason; unwarranted"));
        wordList.add(new Word("Hyperbole", "Exaggerated statements or claims not meant to be taken literally"));
        wordList.add(new Word("Incongruous", "Not in harmony or keeping with the surroundings or other aspects of something"));
        wordList.add(new Word("Judicious", "Having, showing, or done with good judgment or sense"));
        wordList.add(new Word("Keen", "Having a sharp edge or point; eager or enthusiastic"));
        wordList.add(new Word("Lethargy", "A lack of energy and enthusiasm"));
        wordList.add(new Word("Melancholy", "A deep, persistent sadness"));
        wordList.add(new Word("Nostalgic", "Longing for the past"));
        wordList.add(new Word("Omnipotent", "All-powerful; having unlimited power"));
        wordList.add(new Word("Precarious", "Not securely held or in position; dangerously likely to fall or collapse"));
        wordList.add(new Word("Quintessential", "Representing the most perfect or typical example of a quality or class"));
        wordList.add(new Word("Reciprocal", "Given or done in return; mutual"));
        wordList.add(new Word("Soporific", "Tending to induce drowsiness or sleepiness"));
        wordList.add(new Word("Taciturn", "Reserved or uncommunicative in speech; saying little"));
        wordList.add(new Word("Unfathomable", "Incapable of being fully explored or understood"));
        wordList.add(new Word("Vivacious", "Attractively lively and animated"));
        wordList.add(new Word("Whimsical", "Playfully quaint or fanciful, especially in an appealing and amusing way"));
        wordList.add(new Word("Xenophile", "An individual who is attracted to foreign peoples, cultures, or customs"));
        wordList.add(new Word("Yearn", "To have an intense feeling of longing for something, typically something that one has lost or been separated from"));
        wordList.add(new Word("Zealous", "Having or showing zeal; fervent or enthusiastic"));

        // Set the adapter
        WordAdapter adapter = new WordAdapter(wordList);
        recyclerView.setAdapter(adapter);
    }
}
