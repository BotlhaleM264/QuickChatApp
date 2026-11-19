# QuickChatApp
## PROG5121 Part 1, 2 & 3 - Registration, Login, messaging feature for QuickChat messaging app and Storing messages in JSON format
This is a console-based Java application that allows users to register, log in, send messages using the QuickChat messaging system and store messages in JSON format, populating arrays, and a stored messages menu that allows users to search, delete, display the longest message, and view a full report.

### Features
**Part 1
- User registration with username, password, and South African cell phone number
- Username validation (must contain underscore and be no more that 5 characters)
- Password complexity validation (8+ characters, capital letter, number, and special character)
- Cell phone number validation using regex (must start with +27; SA international code)
- Secure login with stored credentials
- Unit tests created using JUnit
**Part 2
- Interactive nuemric menu to send, view, or quit messages
- Ability to input a specific number of messages
- Ato-generated Message Hashes
- Message validation
- Options to send, disregard, or store messages
- 
### How To Run
1. Open the project on netBeans
2. Run 'QuickChatApp.java'
3. Follow the prompts to register, log in, and use the QuickChat messaging menu.

**Part 3
- Stores messages in a JSON file ('stored_messages.json')
- Populates 5 parallel arrays (Sent, Disregarded, Stored, Hashes, IDs)
- Menu Option 4 for "Stored Messages"
- Feature a: Display sender and recipient
- Feature b: Display the longest stored messages
- Feature c: Search for a message by ID
- Feature d: Search for all messages for a specific recipient
- Feature e: Delete a message using the message hash
- Feature f: Display a full report of all stored messages
- Added GitHub Actions for CI/CD automated testing
### References
- JSON Library (org.json): Available at: https://github.com/stleary/JSON-java (Accessed: 18 November 2026).
- SA Phone Number Regex: Stack Overflow. Available at: htps://stackoverflow.com/questions/123559(Accessed: 19 October 2026)
- GitHub Actions CI Documentation: Available at: https://docs.github.com/en/actions (Accessed: 19 November 2026).
### Author
Botlhale Motshwanedi
ST10532331

