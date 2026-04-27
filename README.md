# SAK3R Programme

A comprehensive desktop utility application built with JavaFX for file management, recovery, analysis, and media operations.

## Overview

SAK3R is a feature-rich utility application designed to help users manage files efficiently, recover deleted files, analyze file contents in hexadecimal format, and handle various file operations with an intuitive graphical interface.

## Features

### 🔍 **File Recovery**
- Recover deleted or lost files from your system
- Selective file type filtering (PDF, PNG, JPG, and more)
- Find files based on specific criteria
- Restore recovered files to desired locations

### 📊 **Hex Viewer**
- View file contents in hexadecimal format
- Analyze binary file structures
- Useful for debugging and file inspection
- Support for any file type

### 📁 **File Operations**
- Comprehensive file management tools
- Open, browse, and manipulate files
- Organize and manage your file system
- Batch operations support

### 🎵 **Media Player**
- Play various media files
- Simple and intuitive playback controls
- Media file management

## System Requirements

- **Java**: JDK 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **RAM**: Minimum 512 MB
- **Storage**: 100 MB free space for installation

## Installation

### Prerequisites
Ensure you have Apache Ant and Java Development Kit (JDK) installed on your system.

### Steps

1. **Clone or download the project**
   ```bash
   cd path/to/sak3r
   ```

2. **Build the project**
   ```bash
   ant build
   ```

3. **Run the application**
   ```bash
   ant run
   ```

   Or directly run the JAR file:
   ```bash
   java -jar dist/sak3r.jar
   ```

## Usage

### Launching the Application
1. Run the application using one of the methods above
2. The main window will display with several menu options

### File Recovery
1. Click the **Recovery** button from the main menu
2. Select file types you want to recover (PDF, PNG, JPG, etc.)
3. Choose the location to scan
4. Select the destination folder for recovered files
5. Click **Recover** to start the process

### Hex Viewer
1. Click the **Hex Viewer** button
2. Click **Choose** to select a file
3. The file contents will be displayed in hexadecimal format
4. Use the **Back** button to return to the main menu

### File Operations
1. Select **File Operations** from the menu
2. Browse and manage files as needed
3. Use the available tools to organize your files

### Media Player
1. Click the **Media Player** button to open
2. Select media files to play
3. Use playback controls to manage playback

## Project Structure

```
sak3r/
├── src/sak3r/
│   ├── SAK3R.java                 # Main application entry point
│   ├── FXMLDocumentController.java # Main menu controller
│   ├── RecoveryController.java     # File recovery functionality
│   ├── hexCont.java               # Hex viewer controller
│   ├── linearCont.java            # Linear view controller
│   ├── MainMed.java               # Media player controller
│   ├── projCont.java              # File operations controller
│   ├── FXMLDocument.fxml          # Main window UI
│   ├── Recovery.fxml              # Recovery module UI
│   ├── hex.fxml                   # Hex viewer UI
│   ├── projFXML.fxml              # File operations UI
│   └── image1/                    # Application resources and icons
├── build.xml                       # Ant build configuration
├── manifest.mf                     # JAR manifest file
└── nbproject/                      # NetBeans project configuration
```

## Main Components

| Component | Class | Purpose |
|-----------|-------|---------|
| Application Entry | `SAK3R.java` | Initializes the JavaFX application and loads the main UI |
| Main Menu | `FXMLDocumentController.java` | Manages navigation between different features |
| File Recovery | `RecoveryController.java` | Handles file recovery operations and filtering |
| Hex Viewer | `hexCont.java` | Displays file contents in hexadecimal format |
| File Operations | `projCont.java` | Manages file operations and navigation |
| Media Player | `MainMed.java` | Provides media playback functionality |

## Technologies Used

- **JavaFX**: Modern GUI framework for Java applications
- **Java**: Core programming language
- **FXML**: XML-based UI markup language
- **Apache Ant**: Build automation tool
- **NetBeans**: Development environment

## Building from Source

### Using Ant
```bash
# Clean previous builds
ant clean

# Build the project
ant build

# Create deployable JAR
ant jar

# Run the application
ant run
```

### Using NetBeans IDE
1. Open the project in NetBeans
2. Right-click the project and select **Build**
3. To run, select **Run Project** or press F6

## Troubleshooting

### Application won't start
- Verify Java JDK 8 or higher is installed: `java -version`
- Ensure the build completed successfully
- Check that the JAR file exists in the `dist/` folder

### File Recovery not finding files
- Ensure you have proper permissions to scan the selected directory
- Some recovered data may be fragmented and difficult to recover
- Try scanning a specific folder rather than the entire drive

### Hex Viewer displays garbled content
- This is normal for binary files with non-text data
- The hex viewer is displaying the actual binary structure
- Use the hex viewer for debugging and analysis purposes

## Contributing

To contribute improvements:
1. Fork the project
2. Create a feature branch
3. Make your changes
4. Submit a pull request with a description of your improvements

## License

This project is provided as-is. Modify as needed for your use cases.

## Author

**Author**: SAKER

## Support

For issues, questions, or suggestions, please refer to the project documentation or contact the development team.

## Disclaimer

- Use file recovery features responsibly and legally
- Always backup important data before attempting recovery operations
- The application modifies system files and should be used with caution
- Users are responsible for complying with applicable laws when using this tool

---

**Version**: 1.0
**Last Updated**: 2024
**Status**: Active Development
