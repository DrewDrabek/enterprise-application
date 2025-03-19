using System.ComponentModel.DataAnnotations;

namespace TicketSystem.Models
{
    public enum TicketStatus
    {
        Open,
        InProgress,
        Closed
    }

    public class Ticket
    {
        public int Id { get; set; }

        [Required]
        [StringLength(100, MinimumLength = 3)]
        public string Title { get; set; } = string.Empty;

        [Required]
        [StringLength(500)]
        public string Description { get; set; } = string.Empty;

        public TicketStatus Status { get; set; } = TicketStatus.Open;

        public string AssignedTo { get; set; } = "Unassigned";

        // Computed property to return color code for frontend UI
        public string StatusColor => Status switch
        {
            TicketStatus.Open => "#28a745",        // Green
            TicketStatus.InProgress => "#ffc107",  // Yellow
            TicketStatus.Closed => "#dc3545",      // Red
            _ => "#6c757d"                         // Gray (default)
        };
    }
}
