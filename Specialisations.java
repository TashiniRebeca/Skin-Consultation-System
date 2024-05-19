import java.util.Scanner;

public enum Specialisations
{
    COSMETIC_DERMATOLOGY,
    MEDICAL_DERMATOLOGY,
    PAEDIATRIC_DERMATOLOGY,
    CLINICAL_DERMATOLOGY;

    public static Specialisations chooseSpecialisation()
    {
        Scanner scanner = new Scanner(System.in);
        String spec = "" ;
        int option;
        do
        {
            System.out.println("""
            \nChoose your specialisation from the below list...
            
            1. Cosmetic_Dermatology
            2. Medical_Dermatology
            3. Paediatric_Dermatology
            4. Clinical_Dermatology
            5. If you're finished choosing
            """);
            System.out.print("Select your option: ");
            option = scanner.nextInt();
            switch (option)
            {
                case 1 -> spec = String.valueOf(Specialisations.COSMETIC_DERMATOLOGY);
                case 2 -> spec = String.valueOf(Specialisations.MEDICAL_DERMATOLOGY);
                case 3 -> spec = String.valueOf(Specialisations.PAEDIATRIC_DERMATOLOGY);
                case 4 -> spec = String.valueOf(Specialisations.CLINICAL_DERMATOLOGY);
                case 5 -> System.out.println("\nFinished choosing Specialisation");
                default -> System.out.println("\nWrong option!Try Again...");
            }
        }while (option == 5);
        return Specialisations.valueOf(spec);
    }
}
