package guru.spingframework.spring5recipeapp.bootstrap;

import guru.spingframework.spring5recipeapp.domain.*;
import guru.spingframework.spring5recipeapp.repositories.CategoryRepository;
import guru.spingframework.spring5recipeapp.repositories.RecipeRepository;
import guru.spingframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    Byte[] toObjects(byte[] bytesPrim) {

        Byte[] bytes = new Byte[bytesPrim.length];
        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b; //Autoboxing
        return bytes;

    }
    private byte[] downloadUrl(URL toDownload) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            byte[] chunk = new byte[4096];
            int bytesRead;
            InputStream stream = toDownload.openStream();

            while ((bytesRead = stream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return outputStream.toByteArray();
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(eachUomOptional.isPresent() == false){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(teaspoonUomOptional.isPresent() == false){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(tablespoonUomOptional.isPresent() == false){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(cupUomOptional.isPresent() == false){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if(pinchUomOptional.isPresent() == false){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> poundUomOptional = unitOfMeasureRepository.findByDescription("Pound");
        if(poundUomOptional.isPresent() == false){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if(ounceUomOptional.isPresent() == false){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(dashUomOptional.isPresent() == false){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(pintUomOptional.isPresent() == false){
            throw new RuntimeException("Expected UOM Not Found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure poundUom = poundUomOptional.get();
        UnitOfMeasure ounceUom = ounceUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(americanCategoryOptional.isPresent() == false){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if(italianCategoryOptional.isPresent() == false){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
        if(fastFoodCategoryOptional.isPresent() == false){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(mexicanCategoryOptional.isPresent() == false){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category italianCategory = italianCategoryOptional.get();
        Category fastFoodCategory = fastFoodCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole Recipe");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Guacamole! Did you know that over 2 billion pounds of avocados are consumed each year in the U.S.? (Google it.) That’s over 7 pounds per person. I’m guessing that most of those avocados go into what has become America’s favorite dip, guacamole.\n" +
                "WHERE DOES GUACAMOLE COME FROM?\n" +
                "The word “guacamole”, and the dip, are both originally from Mexico, where avocados have been cultivated for thousands of years. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "INGREDIENTS FOR EASY GUACAMOLE\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity—will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.\n" +
                "GUACAMOLE TIP: USE RIPE AVOCADOS\n" +
                "The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste will be off.\n" +
                "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n" +
                "Remove the pit from the avocado with a chef knife\n" +
                "THE BEST WAY TO CUT AN AVOCADO\n" +
                "To slice open an avocado, cut it in half lengthwise with a sharp chef’s knife and twist apart the sides. One side will have the pit. To remove it, you can do one of two things:\n" +
                "Method #1: Gently tap the pit with your chef’s knife so the knife gets wedged into the pit. Twist your knife slightly to dislodge the pit and lift to remove. If you use this method, first protect your hand with a thick kitchen towel before proceeding.\n" +
                "Method #2: Cut the side with the pit in half again, exposing more of the pit. Use your fingers or a spoon to remove the pit\n" +
                "Once the pit is removed, just cut the avocado into chunks right inside the peel and use a spoon to scoop them out.\n" +
                "Still curious? Read more about How to Cut and Peel an Avocado\n" +
                "Homemade guacamole on a chip\n" +
                "GUACAMOLE VARIATIONS\n" +
                "Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!\n" +
                "Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");

        guacamoleRecipe.setNotes(guacamoleNotes);

        guacamoleRecipe.addIngredient(new Ingredient("2 ripe avocados", new BigDecimal(2) , eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("1/4 teaspoon of salt, more to taste", new BigDecimal(1/4) , teaspoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("1 tablespoon fresh lime juice or lemon juice", new BigDecimal(1) , tablespoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("2 tablespoons to 1/4 cup of minced red onion or thinly sliced green onion", new BigDecimal(2) , eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("1-2 serrano chiles, stems and seeds removed, minced", new BigDecimal(2) , eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("2 tablespoons cilantro (leaves and tender stems), finely chopped", new BigDecimal(2) , tablespoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("A dash of freshly grated black pepper", new BigDecimal(1) , dashUom));
        guacamoleRecipe.addIngredient(new Ingredient("1/2 ripe tomato, seeds and pulp removed, chopped", new BigDecimal(1/2) , eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("Red radishes or jicama, to garnish"));
        guacamoleRecipe.addIngredient(new Ingredient("Tortilla chips, to serve"));

        guacamoleRecipe.getCategories().add(americanCategory);
        guacamoleRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacamoleRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTime(15);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "Spicy Grilled Chicken TacosThe ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");

        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.addIngredient(new Ingredient("2 tablespoons ancho chili powder", new BigDecimal(2) , tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("1 teaspoon dried oregano", new BigDecimal(1) , teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("1 teaspoon dried cumin", new BigDecimal(1) , teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("1 teaspoon sugar", new BigDecimal(1) , teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("1/2 teaspoon salt", new BigDecimal(1/2) , teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("1 clove garlic, finely chopped", new BigDecimal(1) , eachUom));
        tacosRecipe.addIngredient(new Ingredient("1 tablespoon finely grated orange zest", new BigDecimal(1) , tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("3 tablespoons fresh-squeezed orange juice", new BigDecimal(3) , tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("2 tablespoons olive oil", new BigDecimal(2) , tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("4 to 6 skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal(1 + 1/4) , poundUom));

        tacosRecipe.addIngredient(new Ingredient("8 small corn tortillas", new BigDecimal(8) , eachUom));
        tacosRecipe.addIngredient(new Ingredient("3 cups packed baby arugula (3 ounces)", new BigDecimal(3) , ounceUom));
        tacosRecipe.addIngredient(new Ingredient("2 medium ripe avocados, sliced", new BigDecimal(2) , eachUom));
        tacosRecipe.addIngredient(new Ingredient("4 radishes, thinly sliced", new BigDecimal(4) , eachUom));
        tacosRecipe.addIngredient(new Ingredient("1/2 pint cherry tomatoes, halved", new BigDecimal(1/2) , pintUom));
        tacosRecipe.addIngredient(new Ingredient("1/4 red onion, thinly sliced", new BigDecimal(1/4) , eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro"));
        tacosRecipe.addIngredient(new Ingredient("1/2 cup sour cream", new BigDecimal(1/2) , cupUom));
        tacosRecipe.addIngredient(new Ingredient("1/2 cup sour cream thinned with 1/4 cup milk", new BigDecimal(1/4) , cupUom));
        tacosRecipe.addIngredient(new Ingredient("1 lime, cut into wedges", new BigDecimal(1) , eachUom));

        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);

        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private void createGuacamoleRecipe() throws MalformedURLException {
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("How to Make Perfect Guacamole Recipe");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setSource("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setDirections("front");
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setImage(toObjects(downloadUrl(new URL("https://www.simplyrecipes.com/wp-content/uploads/2018/07/Guacamole-LEAD-1-600x840.jpg"))));

        Set<Ingredient> guacamoleIngredients = new HashSet<>();

        Ingredient avocados = new Ingredient();
        avocados.setAmount(BigDecimal.valueOf(2));
        avocados.setDescription("2 ripe avocados");
        guacamoleIngredients.add(avocados);

        Ingredient salt = new Ingredient();
        salt.setAmount(BigDecimal.valueOf(1/4));
        salt.setDescription("1/4 teaspoon of salt, more to taste");
        salt.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        guacamoleIngredients.add(salt);

        Ingredient juice = new Ingredient();
        juice.setAmount(BigDecimal.valueOf(1));
        juice.setDescription("1 tablespoon fresh lime juice or lemon juice");
        juice.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        guacamoleIngredients.add(juice);

        Ingredient onion = new Ingredient();
        onion.setAmount(BigDecimal.valueOf(2 + 1/4));
        onion.setDescription("2 tablespoons to 1/4 cup of minced red onion or thinly sliced green onion");
        onion.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        guacamoleIngredients.add(onion);

        Ingredient chiles = new Ingredient();
        chiles.setAmount(BigDecimal.valueOf(2));
        chiles.setDescription("1-2 serrano chiles, stems and seeds removed, minced");
        guacamoleIngredients.add(chiles);

        Ingredient cilantro  = new Ingredient();
        cilantro.setAmount(BigDecimal.valueOf(2));
        cilantro.setDescription("2 tablespoons cilantro (leaves and tender stems), finely chopped");
        cilantro.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        guacamoleIngredients.add(cilantro);

        Ingredient pepper  = new Ingredient();
        pepper.setAmount(BigDecimal.valueOf(1));
        pepper.setDescription("A dash of freshly grated black pepper");
        pepper.setUom(unitOfMeasureRepository.findByDescription("Dash").get());
        guacamoleIngredients.add(pepper);

        Ingredient tomato  = new Ingredient();
        tomato.setAmount(BigDecimal.valueOf(1/2));
        tomato.setDescription("1/2 ripe tomato, seeds and pulp removed, chopped");
        guacamoleIngredients.add(tomato);

        Ingredient garnish  = new Ingredient();
        garnish.setDescription("Red radishes or jicama, to garnish");
        guacamoleIngredients.add(garnish);

        Ingredient chips  = new Ingredient();
        chips.setDescription("Tortilla chips, to serve");
        guacamoleIngredients.add(chips);

        guacamoleRecipe.setIngredients(guacamoleIngredients);

        recipeRepository.save(guacamoleRecipe);

        System.out.println("Loaded guacamole Recipe");
    }

}
